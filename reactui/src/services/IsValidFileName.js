const isValidFileName = (fileName) => {
    return fileName.endsWith('.txt')
        || fileName.endsWith('.json')
        || fileName.endsWith('.xml');
}

export default isValidFileName;