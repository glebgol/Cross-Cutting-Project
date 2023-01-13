import {useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";
import useFileInput from "../hooks/inputs/useFileInput";
import {ZIP} from "../Urls";

const Zip = () => {
    const file = useFileInput();
    const [outputFile, setOutputFile] = useState('')
    const [isZipped, setIsZipped] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", file.selectedFile);

        fetch(ZIP, {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                setDownloadUri(data.downloadUri);
                setIsZipped(true);
                setOutputFile(data.fileName);
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
                <input type="file" name="file" onChange={(e) => file.onChange(e)} />
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
                    <p>Select a file to show details</p>
                )}
                <button disabled={!file.isPicked}>Zip</button>
            </form>
            <Link to="/">Back to Home Page</Link>
            <button disabled={!isZipped} onClick={download}>Download</button>
        </div>
    );
}

export default Zip;