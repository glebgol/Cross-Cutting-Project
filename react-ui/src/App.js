import './App.css';
import Input from "./Input";

function App() {
  return (
    <div className="App">
      <h1>File Reader</h1>
        <div>
            <div>
                <h3>Enter file name:</h3>
                <input/>
            </div>
            <div>
                <button>
                    Zip
                </button>
                <button>
                    Calculate
                </button>
                <button>
                    Encrypt
                </button>
                <Input>OKK</Input>
            </div>
        </div>
    </div>
  );
}

export default App;
