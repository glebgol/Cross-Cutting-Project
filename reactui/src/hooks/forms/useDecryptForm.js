import useFileInput from "../useFileInput";
import useOutputFileNameInput from "../useOutputFileNameInput";
import useEncryptionKeyInput from "../useEncryptionKeyInput";
import {useEffect, useState} from "react";

const useDecryptForm = () => {
    const file = useFileInput();
    const outputFile = useOutputFileNameInput('');
    const key = useEncryptionKeyInput('');

    const [formValid, setFormValid] = useState(false)
    useEffect(() => {
        if (outputFile.error || key.error || !file.isPicked) {
            setFormValid(false)
        } else {
            setFormValid(true)
        }
    }, [outputFile.error, key.error, file.isPicked])

    return {
        file,
        outputFile,
        key,
        formValid
    }
}

export default useDecryptForm;