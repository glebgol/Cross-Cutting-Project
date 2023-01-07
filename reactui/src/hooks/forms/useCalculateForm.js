import useFileInput from "../inputs/useFileInput";
import useOutputFileNameInput from "../inputs/useOutputFileNameInput";
import useEncryptionKeyInput from "../inputs/useEncryptionKeyInput";
import useFileExtensionInput from "../inputs/useFileExtensionInput";
import {useEffect, useState} from "react";

const useCalculateForm = () => {
    const file = useFileInput();
    const outputFile = useOutputFileNameInput('');
    const encryptionKey = useEncryptionKeyInput('');
    const extension = useFileExtensionInput('Txt');
    const [isZipped, setIsZipped] = useState(false)
    const [isValid, setIsValid] = useState(false)

    const isZippedOnChange = () => {
        setIsZipped(!isZipped)
    }

    useEffect(() => {
        if (outputFile.error || encryptionKey.error || !file.isPicked) {
            setIsValid(false)
        } else {
            setIsValid(true)
        }
    }, [outputFile.error, encryptionKey.error, file.isPicked])

    return {
        file,
        outputFile,
        encryptionKey,
        extension,
        isZipped,
        isValid,
        isZippedOnChange
    }
}

export default useCalculateForm;