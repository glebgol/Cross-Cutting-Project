const fetchDecrypting = (inputFile, outputFile, key, setState) => {
    const url = 'api/file-reader/decrypt/?inputfile=' + inputFile + '&outputfile=' + outputFile + '&key=' + key;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            setState(data.resultInfo);
        });
}

export default fetchDecrypting;