import {useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";

const Decrypt = () => {
    const [selectedFile, setSelectedFile] = useState();
    const [isFilePicked, setIsFilePicked] = useState(false);
    const changeHandler = (event) => {
        setSelectedFile(event.target.files[0]);
        setIsFilePicked(true);
    };

    const [outputFile, setOutputFile] = useState('')
    const [key, setKey] = useState('')
    const [isDecrypted, setIsDecrypted] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", selectedFile);
        formData.append("outputfile", outputFile);
        formData.append("key", key);

        fetch('api/file-reader/decrypt', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                setDownloadUri(data.downloadUri);
                setIsDecrypted(true);
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
                <input
                    type="text"
                    required
                    value={outputFile}
                    onChange={(e) => setOutputFile(e.target.value)}
                />
                <label>Encrypted keys</label>
                <input
                    type="text"
                    value={key}
                    onChange={(e) => {
                        setKey(e.target.value);
                    }}
                />
                <button>Decrypt</button>
            </form>
            <Link to="/">Back to Home Page</Link>
            <button disabled={!isDecrypted} onClick={download}>Download</button>
        </div>
    );
}

export default Decrypt;