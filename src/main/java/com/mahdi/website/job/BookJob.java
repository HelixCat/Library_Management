package com.mahdi.website.job;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.exeception.BusinessException;
import com.mahdi.website.repository.PublisherRepository;
import com.mahdi.website.service.AddressServiceInterface;
import com.mahdi.website.service.PublisherServiceInterface;
import com.mahdi.website.service.validation.PublisherValidationInterface;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BookJob {

    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;
    private final PublisherValidationInterface publisherValidation;
    private final PublisherServiceInterface publisherService;

    @Autowired
    public BookJob(PublisherRepository publisherRepository, ModelMapper modelMapper, PublisherValidationInterface publisherValidation, PublisherServiceInterface publisherService) {
        this.publisherRepository = publisherRepository;
        this.modelMapper = modelMapper;
        this.publisherValidation = publisherValidation;
        this.publisherService = publisherService;

    }

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
        publisherDTO.setAddressDTO(new AddressDTO());
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
            throw new BusinessException("good try to know retry");
        }
    }
}
