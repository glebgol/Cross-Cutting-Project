const fetchUnZip = (inputFile, outputFile, setState) => {
    const url = 'api/file-reader/unzip/?inputfile=' + inputFile + '&outputfile=' + outputFile;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            setState(data.resultInfo);
        })
        .catch(err => {
            console.log(err);
            setState('Server error...');
        });
}

export default fetchUnZip;