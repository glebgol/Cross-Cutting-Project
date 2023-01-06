const isValidEncryptionKeys = (keys) => {
    // keys.split([',', ' ']).forEach(key => {
    //     if (key.length !== 16) {
    //         return false;
    //     }
    // })
    return keys.length === 16 || keys.length === 0;
    //return true;
}

export default isValidEncryptionKeys;