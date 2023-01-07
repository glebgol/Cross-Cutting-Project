import isValidEncryptionKey from "../services/IsValidEncryptionKey";
import {useState} from "react";

const useEncryptionKeyInput = (initialValue) => {
    const [key, setKey] = useState(initialValue)
    const [isDirty, setIsDirty] = useState(false)
    const [error, setError] = useState('')

    const onChange = (e) => {
        setKey(e.target.value)
        if (!isValidEncryptionKey(e.target.value)) {
            setError("Invalid keys")
        } else {
            setError('')
        }
    }

    const onBlur = () => {
        setIsDirty(true)
    }

    return {
        key,
        error,
        isDirty,
        onChange,
        onBlur
    }
}

export default useEncryptionKeyInput;