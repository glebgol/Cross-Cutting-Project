import {useState} from "react";
import {Link} from "react-router-dom";
import downloadFile from "../services/DownloadFile";
import useCalculateForm from "../hooks/forms/useCalculateForm";
import {CALCULATE_URL} from "../Urls";
const Calculate = () => {
    const form = useCalculateForm();

    const [isCalculated, setIsCalculated] = useState(false)
    const [downloadUri, setDownloadUri] = useState('')
    const [resultInfo, setResultInfo] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", form.file.selectedFile);
        formData.append("outputfile", form.outputFile.outputFileName);
        formData.append("iszipped", form.isZipped.toString());
        formData.append("decryptionkeys", form.encryptionKey.key);
        formData.append("extension", form.extension.extension);

        fetch(CALCULATE_URL, {
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

    const download = () => {
        downloadFile(downloadUri, form.outputFile.outputFileName);
    }

    return (
        <div id='calculator'>
            <form onSubmit={handleSubmit}>
                <label>Input file:</label>
                <input type="file" name="file" onChange={e => form.file.onChange(e)} />
                {form.file.isPicked ? (
                    <div id="selectedFile">
                        <p>Filename: {form.file.selectedFile.name}</p>
                        <p>Filetype: {form.file.selectedFile.type}</p>
                        <p>Size in bytes: {form.file.selectedFile.size}</p>
                        <p>
                            lastModifiedDate:{' '}
                            {form.file.selectedFile.lastModifiedDate.toLocaleDateString()}
                        </p>
                    </div>
                ) : (
                    <p>Select a file to calculate</p>
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
                <label>Is zipped?</label>
                <input
                    type="checkbox"
                    value={form.isZipped}
                    onChange={() => {
                        form.isZippedOnChange(!form.isZipped);
                    }}
                />
                <label>Encrypted keys</label>
                {(form.encryptionKey.isDirty && form.encryptionKey.error) && <div style={{color:'red'}}>{form.encryptionKey.error}</div>}
                <input
                    type="text"
                    name='key'
                    value={form.encryptionKey.value}
                    onChange={(e) => form.encryptionKey.onChange(e)}
                    onBlur={(e) => form.encryptionKey.onBlur(e)}
                />
                <select
                    value={form.extension.value}
                    onChange={(e) => form.extension.onChange(e)}
                >
                    <option value="txt">Txt</option>
                    <option value="json">Json</option>
                    <option value="xml">Xml</option>
                </select>
                <button name='calculate' disabled={!form.isValid}>Calculate</button>
            </form>
            <p><b>{resultInfo}</b></p>
            <Link to="/">Back to Home Page</Link>
            <button name='download' disabled={!isCalculated} onClick={download}>Download</button>
        </div>
    );
}

export default Calculate;