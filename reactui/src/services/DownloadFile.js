import axios from "axios";
import fileDownload from "js-file-download";

const downloadFile = (downloadUri, fileName) => {
    axios.get(downloadUri, {
        responseType: 'blob',
    })
        .then((res) => {
            fileDownload(res.data, fileName)
        })
}

export default downloadFile;