const isValidEncryptionKey = (keys) => {
    return keys.length === 16;
}

export default isValidEncryptionKey;