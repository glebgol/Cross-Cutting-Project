const fetchZip = (inputFile, setState) => {
    const url = 'api/file-reader/zip/?inputfile=' + inputFile;
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

export default fetchZip;