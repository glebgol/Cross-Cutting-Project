const fetchEncrypting = (inputFile, outputFile, key, setState) => {
    const url = 'api/file-reader/encrypt/?inputfile=' + inputFile + '&outputfile=' + outputFile + '&key=' + key;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            setState(data.resultInfo);
        });
}

export default fetchEncrypting;