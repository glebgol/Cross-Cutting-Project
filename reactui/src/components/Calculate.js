import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";
import useOutputFileNameInput from "../hooks/useOutputFileNameInput";
import useEncryptionKeyInput from "../hooks/useEncryptionKeyInput";

const Calculate = () => {
    const [selectedFile, setSelectedFile] = useState();
    const [isFilePicked, setIsFilePicked] = useState(false);
    const changeHandler = (event) => {
        setSelectedFile(event.target.files[0]);
        setIsFilePicked(true);
    };

    const outputFile = useOutputFileNameInput('');
    const encryptionKey = useEncryptionKeyInput('');

    const [isZipped, setIsZipped] = useState(false)

    const [extension, setExtension] = useState('Txt')

    const [isCalculated, setIsCalculated] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')

    const [resultInfo, setResultInfo] = useState('')

    const [formValid, setFormValid] = useState(false)
    useEffect(() => {
        if (outputFile.error || encryptionKey.error || !isFilePicked) {
            setFormValid(false)
        } else {
            setFormValid(true)
        }
    })

    const download = () => {
        downloadFile(downloadUri, outputFile.outputFileName);
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", selectedFile);
        formData.append("outputfile", outputFile.outputFileName);
        formData.append("iszipped", isZipped.toString());
        formData.append("decryptionkeys", encryptionKey.key);
        formData.append("extension", extension);

        fetch('api/file-reader/calculate', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                setDownloadUri(data.downloadUri);
                setIsCalculated(true);
                setResultInfo('Successfully calculated!')
                console.log(downloadUri);
            })
            .catch(err => {
                setResultInfo('Server error!')
                console.log(err);
            });
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>Input file:</label>
                <input type="file" name="file" onChange={changeHandler} />
                {isFilePicked ? (
                    <div>
                        <p>Filename: {selectedFile.name}</p>
                        <p>Filetype: {selectedFile.type}</p>
                        <p>Size in bytes: {selectedFile.size}</p>
                        <p>
                            lastModifiedDate:{' '}
                            {selectedFile.lastModifiedDate.toLocaleDateString()}
                        </p>
                    </div>
                ) : (
                    <p>Select a file to calculate</p>
                )}
                <label>Output file:</label>
                {(outputFile.isDirty && outputFile.error) && <div style={{color:'red'}}>{outputFile.error}</div>}
                <input
                    placeholder='Enter output file name:'
                    name='outputFile'
                    type="text"
                    required
                    value={outputFile.value}
                    onBlur={e => outputFile.onBlur(e)}
                    onChange={e => outputFile.onChange(e)}
                />
                <label>Is zipped?</label>
                <input
                    type="checkbox"
                    onChange={() => {
                        setIsZipped(!isZipped);
                    }}
                />
                <label>Encrypted keys</label>
                {(encryptionKey.isDirty && encryptionKey.error) && <div style={{color:'red'}}>{encryptionKey.error}</div>}
                <input
                    type="text"
                    value={encryptionKey.value}
                    onChange={(e) => encryptionKey.onChange(e)}
                    onBlur={(e) => encryptionKey.onBlur(e)}
                />
                <select
                    value={extension}
                    onChange={(e) => {
                        setExtension(e.target.value);
                    }}
                >
                    <option value="txt">Txt</option>
                    <option value="json">Json</option>
                    <option value="xml">Xml</option>
                </select>
                <button disabled={!formValid}>Calculate</button>
            </form>
            <p><b>{resultInfo}</b></p>
            <Link to="/">Back to Home Page</Link>
            <button disabled={!isCalculated} onClick={download}>Download</button>
        </div>
    );
}

export default Calculate;