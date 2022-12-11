import './App.css';
import Calculate from "./components/Calculate";
import Decrypt from "./components/Decrypt";
import UnZip from "./components/UnZip";
import Zip from "./components/Zip";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import Encrypt from "./components/Encrypt";

function App() {
  return (
      <BrowserRouter>
        <div className="App">
            <Navbar />
            <div>
                <Routes>
                    <Route exact path="/" element={<Home />} />
                    <Route path="calculate/" element={<Calculate />} />
                    <Route path="decrypt/" element={<Decrypt />} />
                    <Route path="encrypt/" element={<Encrypt />} />
                    <Route path="zip/" element={<Zip />} />
                    <Route path="unzip/" element={<UnZip />} />
                </Routes>
            </div>
        </div>
      </BrowserRouter>
  );
}

export default App;
