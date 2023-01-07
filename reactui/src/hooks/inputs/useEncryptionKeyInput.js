import isValidEncryptionKey from "../../services/IsValidEncryptionKey";
import {useState} from "react";

const useEncryptionKeyInput = (initialValue, required) => {
    const [key, setKey] = useState(initialValue)
    const [isDirty, setIsDirty] = useState(false)
    const [error, setError] = useState(required ? 'Encryption key can\'t be empty' : '')

    const onChange = (e) => {
        setKey(e.target.value)
        if (required) {
            if (!isValidEncryptionKey(e.target.value)) {
                setError("Invalid keys")
            }
            else {
                setError('')
            }
        }
        else if (!isValidEncryptionKey(e.target.value) && e.target.value.length !== 0) {
            setError("Invalid keys")
        }
        else {
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