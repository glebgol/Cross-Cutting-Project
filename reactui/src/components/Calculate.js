import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";
import isValidFileName from "../services/IsValidFileName";
import isValidEncryptionKey from "../services/IsValidEncryptionKey";

const Calculate = () => {
    const [selectedFile, setSelectedFile] = useState();
    const [isFilePicked, setIsFilePicked] = useState(false);
    const changeHandler = (event) => {
        setSelectedFile(event.target.files[0]);
        setIsFilePicked(true);
    };

    const [outputFile, setOutputFile] = useState('')
    const [outputFileIsDirty, setOutputFileIsDirty] = useState(false)
    const [outputFileError, setOutputFileError] = useState('Output file name can\'t be empty')

    const [isZipped, setIsZipped] = useState(false)

    const [keys, setKeys] = useState('')
    const [keysIsDirty, setKeysIsDirty] = useState(false)
    const [keysError, setKeysError] = useState('')

    const [extension, setExtension] = useState('Txt')

    const [isCalculated, setIsCalculated] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')

    const [resultInfo, setResultInfo] = useState('')

    const [formValid, setFormValid] = useState(false)
    useEffect(() => {
        if (outputFileError || keysError || !isFilePicked) {
            setFormValid(false)
        } else {
            setFormValid(true)
        }
    })

    const outputFileBlurHandler = () => {
        setOutputFileIsDirty(true)
    }
    const outputFileHandler = (e) => {
        setOutputFile(e.target.value)
        if (!isValidFileName(e.target.value)) {
            setOutputFileError("Invalid filename")
        } else {
            setOutputFileError('')
        }
    }
    const keysHandler = (e) => {
        setKeys(e.target.value)
        if (!isValidEncryptionKey(e.target.value)) {
            setKeysError("Invalid keys")
        } else {
            setKeysError('')
        }
    }
    const download = () => {
        downloadFile(downloadUri, outputFile);
    }
    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", selectedFile);
        formData.append("outputfile", outputFile);
        formData.append("iszipped", isZipped.toString());
        formData.append("decryptionkeys", keys);
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

    function keysBlurHandler() {
        setKeysIsDirty(true)
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
                {(outputFileIsDirty && outputFileError) && <div style={{color:'red'}}>{outputFileError}</div>}
                <input
                    placeholder='Enter output file name:'
                    name='outputFile'
                    type="text"
                    required
                    value={outputFile}
                    onBlur={outputFileBlurHandler}
                    onChange={e => outputFileHandler(e)}
                />
                <label>Is zipped?</label>
                <input
                    type="checkbox"
                    onChange={() => {
                        setIsZipped(!isZipped);
                    }}
                />
                <label>Encrypted keys</label>
                {(keysIsDirty && keysError) && <div style={{color:'red'}}>{keysError}</div>}
                <input
                    type="text"
                    value={keys}
                    onChange={(e) => keysHandler(e)}
                    onBlur={keysBlurHandler}
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