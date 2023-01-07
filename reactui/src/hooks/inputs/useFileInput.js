import {useState} from "react";

const useFileInput = () => {
    const [selectedFile, setSelectedFile] = useState();
    const [isPicked, setIsFilePicked] = useState(false);
    const onChange = (e) => {
        setSelectedFile(e.target.files[0]);
        setIsFilePicked(true);
    };

    return {
        selectedFile,
        isPicked,
        onChange
    }
};

export default useFileInput;