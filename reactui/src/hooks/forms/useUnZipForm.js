import React, {useEffect, useState} from 'react';
import useFileInput from "../inputs/useFileInput";
import useOutputFileNameInput from "../inputs/useOutputFileNameInput";

const useUnZipForm = () => {
    const file = useFileInput();
    const outputFile = useOutputFileNameInput();

    const [formValid, setFormValid] = useState(false)
    useEffect(() => {
        if (outputFile.error || !file.isPicked) {
            setFormValid(false)
        } else {
            setFormValid(true)
        }
    }, [outputFile.error, file.isPicked])

    return {
        file,
        outputFile,
        formValid
    }
};

export default useUnZipForm;