import {useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";
import useEncryptForm from "../hooks/forms/useEncryptForm";
import {ENCRYPT, PROXY} from "../Urls"
const Encrypt = () => {
    const form = useEncryptForm();

    const [isEncrypted, setIsEncrypted] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')

    const download = () => {
        downloadFile(downloadUri, form.outputFile.outputFileName);
    }

    const handleEncrypt = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("inputFile", form.file.selectedFile);
        formData.append("outputFilename", form.outputFile.outputFileName);
        formData.append("key", form.key.key);

        fetch(ENCRYPT, {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                setDownloadUri(data.downloadUri);
                setIsEncrypted(true);
                console.log(downloadUri);
            })
            .catch(err => {
                console.log(err);
            });
    }

    return (
        <div>
            <form onSubmit={handleEncrypt}>
                <label>Input file:</label>
                <input type="file" name="file" onChange={e => form.file.onChange(e)} />
                {form.file.isPicked ? (
                    <div>
                        <p>Filename: {form.file.selectedFile.name}</p>
                        <p>Filetype: {form.file.selectedFile.type}</p>
                        <p>Size in bytes: {form.file.selectedFile.size}</p>
                        <p>
                            lastModifiedDate:{' '}
                            {form.file.selectedFile.lastModifiedDate.toLocaleDateString()}
                        </p>
                    </div>
                ) : (
                    <p>Select a file to show details</p>
                )}
                <label>Output file:</label>
                {(form.outputFile.isDirty && form.outputFile.error) && <div style={{color:'red'}}>{form.outputFile.error}</div>}
                <input
                    placeholder='Enter output file name:'
                    name='outputFile'
                    type="text"
                    required
                    value={form.outputFile.value}
                    onBlur={e => form.outputFile.onBlur(e)}
                    onChange={e => form.outputFile.onChange(e)}
                />
                <label>Encrypted keys</label>
                {(form.key.isDirty && form.key.error) && <div style={{color:'red'}}>{form.key.error}</div>}
                <input
                    type="text"
                    required
                    name='key'
                    value={form.key.value}
                    onChange={(e) => form.key.onChange(e)}
                    onBlur={(e) => form.key.onBlur(e)}
                />
                <button name="encrypt" disabled={!form.formValid}>Encrypt</button>
            </form>
            <Link to="/">Back to Home Page</Link>
            <button name="download" disabled={!isEncrypted} onClick={download}>Download</button>
            <a id='#download' href={PROXY + downloadUri} download="">Download link</a>
        </div>
    );
}

export default Encrypt;