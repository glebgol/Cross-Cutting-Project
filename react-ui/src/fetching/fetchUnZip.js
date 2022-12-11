const fetchUnZip = (inputFile, outputFile, setState) => {
    const url = 'api/file-reader/unzip/?inputfile=' + inputFile + '&outputfile=' + outputFile;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            setState(data.resultInfo);
        });
}

export default fetchUnZip;