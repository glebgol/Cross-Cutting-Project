import {useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";

const Zip = () => {
    const [selectedFile, setSelectedFile] = useState();
    const [isFilePicked, setIsFilePicked] = useState(false);
    const [outputFile, setOutputFile] = useState('')
    const [isZipped, setIsZipped] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')
    const changeHandler = (event) => {
        setSelectedFile(event.target.files[0]);
        setIsFilePicked(true);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", selectedFile);

        fetch('api/file-reader/zip', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                setDownloadUri(data.downloadUri);
                setIsZipped(true);
                setOutputFile(data.FileName);
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
                <button>Zip</button>
            </form>
            <Link to="/">Back to Home Page</Link>
            <button disabled={!isZipped} onClick={download}>Download</button>
        </div>
    );
}

export default Zip;