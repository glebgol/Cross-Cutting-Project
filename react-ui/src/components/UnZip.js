import {useState} from "react";
import {Link} from "react-router-dom";
import fetchUnZip from "../fetching/fetchUnZip";

const UnZip = () => {
    const [inputFile, setInputFile] = useState('')
    const [outputFile, setOutputFile] = useState('')
    const [resultInfo, setResultInfo] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault();
        fetchUnZip(inputFile, outputFile, setResultInfo);
    }
    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>Input file:</label>
                <input
                    type="text"
                    required
                    value={inputFile}
                    onChange={(e) => setInputFile(e.target.value)}
                />
                <label>Output file:</label>
                <input
                    type="text"
                    required
                    value={outputFile}
                    onChange={(e) => setOutputFile(e.target.value)}
                />
                <button>UnZip</button>
            </form>
            <p><b>{resultInfo}</b></p>
            <Link to="/">Back to Home Page</Link>
        </div>
    );
}

export default UnZip;