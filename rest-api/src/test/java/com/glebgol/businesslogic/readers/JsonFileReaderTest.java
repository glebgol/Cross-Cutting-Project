package com.glebgol.businesslogic.readers;

import com.glebgol.businesslogic.contracts.exceptions.CryptoException;
import com.glebgol.businesslogic.contracts.interfaces.IFileReader;
import com.glebgol.businesslogic.contracts.interfaces.IStream;
import com.glebgol.providers.StreamArguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

class JsonFileReaderTest {
    @Test
    public void Calculate() throws IOException, CryptoException, JAXBException {
        IFileReader spyReader = Mockito.spy(new JsonFileReader(null));
        IStream streamForCalculation = StreamArguments.JsonStream();
        IStream expectedCalculationResult = StreamArguments.JsonCalculationStream();
        List<String> expectedCalculationResultLines = expectedCalculationResult.lines();

        IStream calculationResult = spyReader.calculate(streamForCalculation);
        List<String> calculationResultLines = calculationResult.lines();

        Assertions.assertEquals(expectedCalculationResultLines, calculationResultLines);
    }
}