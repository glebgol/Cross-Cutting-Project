import isValidFileName from "../../services/IsValidFileName";
import {useState} from "react";

const useOutputFileNameInput = (initialValue) => {
    const [outputFileName, setOutputFileName] = useState(initialValue)
    const [isDirty, setIsDirty] = useState(false)
    const [error, setError] = useState('Output file name can\'t be empty')

    const onChange = (e) => {
        setOutputFileName(e.target.value)
        if (!isValidFileName(e.target.value)) {
            setError("Invalid filename")
        } else {
            setError('')
        }
    }

    const onBlur = () => {
        setIsDirty(true)
    }

    return {
        outputFileName,
        error,
        isDirty,
        onChange,
        onBlur
    }
}

export default useOutputFileNameInput;