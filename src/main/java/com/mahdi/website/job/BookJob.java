package com.mahdi.website.job;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.exception.global.BusinessException;
import com.mahdi.website.repository.PublisherRepository;
import com.mahdi.website.service.interfaces.PublisherServiceInterface;
import com.mahdi.website.service.validation.interfaces.PublisherValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class BookJob {

    private final PublisherRepository publisherRepository;
    private final PublisherValidationInterface publisherValidation;
    private final PublisherServiceInterface publisherService;

    // run every 1 sec
    @Scheduled(cron = "*/1 * * * * ?")
    public void addPublisher() {
        publisherService.savePublisher(ceratePublisherDTO());
        System.out.println("Publisher added");
    }

    private PublisherDTO ceratePublisherDTO() {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setActive(Boolean.TRUE);
        publisherDTO.setName("entesharate fati khoshgle");
        publisherDTO.setEmail("fati@gmail.com");
        publisherDTO.setPhoneNumber("09352877455");
        return publisherDTO;
    }



    @Retryable(
            value = {BusinessException.class},
            backoff = @Backoff(
                    delay = 1_000,
                    multiplier = 60))
    public void testRetryJobs(PublisherDTO publisherDTO) throws BusinessException {
        displayMessage(publisherDTO, 5);
    }

    private static void displayMessage(PublisherDTO publisherDTO, int number) {
        if (number < 5) {
            throw new BusinessException();
        }
    }
}
