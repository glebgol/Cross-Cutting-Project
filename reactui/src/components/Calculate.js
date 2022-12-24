import {useState} from "react";
import {Link} from "react-router-dom";
import fetchCalculating from "../fetching/fetchCalculating";

const Calculate = () => {
    const [inputFile, setInputFile] = useState('')
    const [inputFileIsDirty, setInputFileIsDirty] = useState(false)
    const [inputFileError, setInputFileError] = useState('Input file name can\'t be empty')

    const [outputFile, setOutputFile] = useState('')
    const [outputFileIsDirty, setOutputFileIsDirty] = useState(false)
    const [outputFileError, setOutputFileError] = useState('Output file name can\'t be empty')

    const [isZipped, setIsZipped] = useState(false)
    const [keys, setKeys] = useState('')

    const [extension, setExtension] = useState('Txt')
    const [resultInfo, setResultInfo] = useState('')

    const [downloadUri, setDownloadUri] = useState('')

    const inputFileBlurHandler = (e) => {
        setInputFileIsDirty(true)
    }
    const outputFileBlurHandler = (e) => {
        setOutputFileIsDirty(true)
    }

    const inputFileOnChangeHandler = (e) => {
        setInputFile(e.target.value)

        //todo validation
        const allowedExtensions =
            '.txt';

        if (e.target.value !== '' && !allowedExtensions.exec(e.target.value)) {
            setInputFileError('Not valid file name')
        } else {
            setInputFile(e.target.value)
            setInputFileError('')
        }
    }
    const handleSubmit = (e) => {
        e.preventDefault();

        let url = 'api/file-reader/calculate/?inputfile=' + inputFile + '&outputfile=' + outputFile +
            '&iszipped=' + isZipped + '&extension=' + extension;
        if (keys !== '') {
            url += '&decryptionkeys=' + keys
        }
        fetch(url,  {
            method: 'POST'
        })
            .then(response => response.json())
            .then(data => {
                setDownloadUri(data.downloadUri);
                console.log(downloadUri);
            })
            .catch(err => {
                console.log(err);
            });
    }
    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>Input file:</label>
                {(inputFileIsDirty && inputFileError) && <div style={{color:'red'}}>{inputFileError}</div>}
                <input
                    placeholder='Enter input file name:'
                    name='inputFile'
                    type="file"
                    required
                    value={inputFile}
                    onChange={(e) => {
                        setInputFile(e.target.value);
                    }}                />
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
        </div>
    );
}

export default Calculate;