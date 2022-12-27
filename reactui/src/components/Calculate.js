import {useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";

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

    const [extension, setExtension] = useState('Txt')
    const [resultInfo, setResultInfo] = useState('')

    const [isCalculated, setIsCalculated] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')


    const outputFileBlurHandler = (e) => {
        setOutputFileIsDirty(true)
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
                console.log(downloadUri);
            })
            .catch(err => {
                console.log(err);
            });
    }
    const download = () => {
        downloadFile(downloadUri, outputFile);
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
                    <p>Select a file to show details</p>
                )}
                <label>Output file:</label>
                {(outputFileIsDirty && outputFileError) && <div style={{color:'red'}}>{outputFileError}</div>}
                <input
                    placeholder='Enter output file name:'
                    name='outputFile'
                    type="text"
                    required
                    value={outputFile}
                    onBlur={e => outputFileBlurHandler(e)}
                    onChange={(e) => setOutputFile(e.target.value)}
                />
                <label>Is zipped?</label>
                <input
                    type="checkbox"
                    onChange={() => {
                        setIsZipped(!isZipped);
                    }}
                />
                <label>Encrypted keys</label>
                <input
                    type="text"
                    value={keys}
                    onChange={(e) => {
                        setKeys(e.target.value);
                    }}
                />
                <select
                    value={extension}
                    onChange={(e) => {
                        setExtension(e.target.value);
                        console.log(e.target.value);
                    }}
                >
                    <option value="txt">Txt</option>
                    <option value="json">Json</option>
                    <option value="xml">Xml</option>
                </select>
                <button>Calculate</button>
            </form>
            <p><b>{resultInfo}</b></p>
            <Link to="/">Back to Home Page</Link>
            <button disabled={!isCalculated} onClick={download}>Download</button>
        </div>
    );
}

export default Calculate;