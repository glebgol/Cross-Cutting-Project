const fetchCalculating = (inputFile, outputFile, isZipped, keys, extension, setState) => {
    let url = 'api/file-reader/calculate/?inputfile=' + inputFile + '&outputfile=' + outputFile +
        '&iszipped=' + isZipped + '&extension=' + extension;
    if (keys !== '') {
        url += '&decryptionkeys=' + keys
    }
    fetch(url)
        .then(response => response.json())
        .then(data => {
            setState(data.resultInfo);
        })
        .catch(err => {
            console.log(err);
            setState('Server error');
        });
}

export default fetchCalculating;