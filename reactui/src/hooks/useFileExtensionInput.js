import React, {useState} from 'react';

const useFileExtensionInput = (initialValue) => {
    const [extension, setExtension] = useState(initialValue)
    const [error, setError] = useState()

    const onChange = (e) => {
        if (e.target.value !== 'txt' || e.target.value !== 'json' || e.target.value !== 'xml') {
            setError('Not valid')
        }
        setExtension(e.target.value);
    }

    return {
        error,
        extension,
        onChange
    }
};

export default useFileExtensionInput;