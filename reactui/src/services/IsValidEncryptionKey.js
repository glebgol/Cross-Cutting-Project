const isValidEncryptionKey = (keys) => {
    return keys.length === 16 || keys.length === 0;
}

export default isValidEncryptionKey;