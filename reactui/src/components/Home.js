import {Link} from "react-router-dom";

const Home = () => {
    return (
        <div>
            <Link to="/calculate">
                <button name="Calculate">Calculate!</button>
            </Link>
            <Link to="/zip">
                <button name="Zip">Zip!</button>
            </Link>
            <Link to="/unzip">
                <button name="UnZip">UnZip!</button>
            </Link>
            <Link to="/encrypt">
                <button name="Encrypt">Encrypt!</button>
            </Link>
            <Link to="/decrypt">
                <button name="Decrypt">Decrypt!</button>
            </Link>
        </div>
    );
}

export default Home;