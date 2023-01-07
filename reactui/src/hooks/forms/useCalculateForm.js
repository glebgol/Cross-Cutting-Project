import useFileInput from "../useFileInput";
import useOutputFileNameInput from "../useOutputFileNameInput";
import useEncryptionKeyInput from "../useEncryptionKeyInput";
import useFileExtensionInput from "../useFileExtensionInput";

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
    }, [outputFile, encryptionKey, file])

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