import {useState} from "react";
import {Link} from "react-router-dom";
import fetchZip from "../fetching/fetchZip";

const Zip = () => {
    const [inputFile, setInputFile] = useState('')
    const [resultInfo, setResultInfo] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault();
        fetchZip(inputFile, setResultInfo);
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
                <button>Zip</button>
            </form>
            <p><b>{resultInfo}</b></p>
            <Link to="/">Back to Home Page</Link>
        </div>
    );
}

export default Zip;