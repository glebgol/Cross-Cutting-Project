import useFileInput from "../inputs/useFileInput";
import useOutputFileNameInput from "../inputs/useOutputFileNameInput";
import useEncryptionKeyInput from "../inputs/useEncryptionKeyInput";
import {useEffect, useState} from "react";

const useDecryptForm = () => {
    const file = useFileInput();
    const outputFile = useOutputFileNameInput('');
    const key = useEncryptionKeyInput('', true);

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