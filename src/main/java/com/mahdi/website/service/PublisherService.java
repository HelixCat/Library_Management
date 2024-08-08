package com.mahdi.website.service;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.repository.PublisherRepository;
import com.mahdi.website.repository.PublisherSearchSpecification;
import com.mahdi.website.service.validation.PublisherValidationInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mahdi.website.exeception.PublisherNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherService implements PublisherServiceInterface {

    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;
    private final PublisherValidationInterface publisherValidation;
    private final AddressServiceInterface addressService;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository, ModelMapper modelMapper, PublisherValidationInterface publisherValidation, AddressServiceInterface addressService) {
        this.publisherRepository = publisherRepository;
        this.modelMapper = modelMapper;
        this.publisherValidation = publisherValidation;
        this.addressService = addressService;
    }

    @Override
    public List<PublisherDTO> searchPublisher(PublisherDTO publisherDTO) {
        PublisherSearchSpecification specification = new PublisherSearchSpecification(publisherDTO);
        List<Publisher> publisherList = publisherRepository.findAll(specification);
        return preparePublisherDTOList(publisherList);
    }

    @Override
    public Publisher savePublisher(PublisherDTO publisherDTO) {
        publisherValidation.addPublisherValidation(publisherDTO);
        Publisher publisher = preparePublisher(publisherDTO);
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher findPublisherByName(String name) {
        return publisherRepository.findByPublisherName(name)
                .orElseThrow(() -> new PublisherNotFoundException("publisher with name " + name + " does not exist"));
    }

    @Override
    public Publisher findPublisherByEmail(String email) {
        return publisherRepository.findPublisherByEmail(email)
                .orElseThrow(() -> new PublisherNotFoundException("publisher with email " + email + " does not exist"));
    }

    @Override
    public Publisher findPublisherByPhoneNumber(String phoneNumber) {
        return publisherRepository.findPublisherByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new PublisherNotFoundException("publisher with phone number " + phoneNumber + " does not exist"));
    }

    @Override
    public PublisherDTO findPublisherDTOById(Long id) {
        Publisher publisher = findPublisherById(id);
        PublisherDTO publisherDTO = modelMapper.map(publisher, PublisherDTO.class);
        publisherDTO.setAddressDTO(modelMapper.map(publisher.getAddresses().getFirst(), AddressDTO.class));
        return publisherDTO;
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new PublisherNotFoundException("publisher with id " + id + " does not exist"));
    }

    @Override
    public void deactivatePublisherById(Long id) {
        Publisher publisher = findPublisherById(id);
        publisher.setActive(Boolean.FALSE);
        publisherRepository.save(publisher);
    }

    public void updatePublisher(Long id, PublisherDTO publisherDTO) {
        Publisher publisher = findPublisherById(id);
        publisherValidation.updatePublisherValidation(publisher, publisherDTO);
        publisher.setName(publisherDTO.getName());
        publisher.setEmail(publisherDTO.getEmail());
        publisher.setPhoneNumber(publisherDTO.getPhoneNumber());
        publisher.setActive(publisherDTO.getActive());
        addressService.updatePublisherAddress(publisherDTO.getAddressDTO());
        publisherRepository.save(publisher);
    }


    private Publisher preparePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = modelMapper.map(publisherDTO, Publisher.class);
        publisher.setActive(Boolean.TRUE);
        List<Address> addressList = prepareAddress(publisherDTO.getAddressDTO(), publisher);
        publisher.setAddresses(addressList);
        return publisher;
    }

    private List<PublisherDTO> preparePublisherDTOList(List<Publisher> publisherList) {
        List<PublisherDTO> publisherDTOList = new ArrayList<>();
        for (Publisher publisher : publisherList) {
            PublisherDTO publisherDTO = preparePublisherDTO(publisher);
            publisherDTOList.add(publisherDTO);
        }
        return publisherDTOList;
    }

    private PublisherDTO preparePublisherDTO(Publisher publisher) {
        return modelMapper.map(publisher, PublisherDTO.class);
    }

    private List<Address> prepareAddress(AddressDTO addressDTO, Publisher publisher) {
        List<Address> addressList = new ArrayList<>();
        Address address = modelMapper.map(addressDTO, Address.class);
        address.setActive(Boolean.TRUE);
        address.setPublisher(publisher);
        addressList.add(address);
        return addressList;
    }
}
