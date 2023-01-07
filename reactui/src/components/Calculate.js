import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";
import useOutputFileNameInput from "../hooks/useOutputFileNameInput";
import useEncryptionKeyInput from "../hooks/useEncryptionKeyInput";
import useFileInput from "../hooks/useFileInput";
import useFileExtensionInput from "../hooks/useFileExtensionInput";

const Calculate = () => {
    const file = useFileInput();
    const outputFile = useOutputFileNameInput('');
    const encryptionKey = useEncryptionKeyInput('');
    const extension = useFileExtensionInput('Txt');
    const [isZipped, setIsZipped] = useState(false)

    const [isCalculated, setIsCalculated] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')
    const [resultInfo, setResultInfo] = useState('')

    const [formValid, setFormValid] = useState(false)
    useEffect(() => {
        if (outputFile.error || encryptionKey.error || !file.isPicked) {
            setFormValid(false)
        } else {
            setFormValid(true)
        }
    }, [outputFile.error, encryptionKey.error, file.isPicked])

    const download = () => {
        downloadFile(downloadUri, outputFile.outputFileName);
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", file.selectedFile);
        formData.append("outputfile", outputFile.outputFileName);
        formData.append("iszipped", isZipped.toString());
        formData.append("decryptionkeys", encryptionKey.key);
        formData.append("extension", extension.extension);

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
                <input type="file" name="file" onChange={e => file.onChange(e)} />
                {file.isPicked ? (
                    <div>
                        <p>Filename: {file.selectedFile.name}</p>
                        <p>Filetype: {file.selectedFile.type}</p>
                        <p>Size in bytes: {file.selectedFile.size}</p>
                        <p>
                            lastModifiedDate:{' '}
                            {file.selectedFile.lastModifiedDate.toLocaleDateString()}
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
                    value={isZipped}
                    onChange={() => {
                        setIsZipped(!isZipped);
                        console.log(isZipped)
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
                    value={extension.value}
                    onChange={(e) => extension.onChange(e)}
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