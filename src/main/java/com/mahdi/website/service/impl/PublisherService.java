package com.mahdi.website.service.impl;

import com.mahdi.website.dto.AddressDTO;
import com.mahdi.website.dto.PublisherDTO;
import com.mahdi.website.mapper.AddressMapper;
import com.mahdi.website.mapper.PublisherMapper;
import com.mahdi.website.model.Address;
import com.mahdi.website.model.Publisher;
import com.mahdi.website.repository.PublisherRepository;
import com.mahdi.website.repository.PublisherSearchSpecification;
import com.mahdi.website.service.interfaces.AddressServiceInterface;
import com.mahdi.website.service.interfaces.PublisherServiceInterface;
import com.mahdi.website.service.validation.interfaces.PublisherValidationInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mahdi.website.exception.publisher.PublisherNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService implements PublisherServiceInterface {

    private final AddressMapper addressMapper;
    private final PublisherMapper publisherMapper;
    private final AddressServiceInterface addressService;
    private final PublisherRepository publisherRepository;
    private final PublisherValidationInterface publisherValidation;

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
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    public Publisher findPublisherByEmail(String email) {
        return publisherRepository.findPublisherByEmail(email)
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    public Publisher findPublisherByPhoneNumber(String phoneNumber) {
        return publisherRepository.findPublisherByPhoneNumber(phoneNumber)
                .orElseThrow(PublisherNotFoundException::new);
    }

    @Override
    public PublisherDTO findPublisherDTOById(Long id) {
        Publisher publisher = findPublisherById(id);
        PublisherDTO publisherDTO = publisherMapper.toDTO(publisher);
        publisherDTO.setAddressDTO(addressMapper.toDTO(publisher.getAddresses().getFirst()));
        return publisherDTO;
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(PublisherNotFoundException::new);
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
        addressService.updateAddress(publisherDTO.getAddressDTO());
        publisherRepository.save(publisher);
    }


    private Publisher preparePublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        publisher.setActive(Boolean.TRUE);
        List<Address> addressList = prepareAddress(publisherDTO.getAddressDTO(), publisher);
        publisher.setAddresses(addressList);
        return publisher;
    }

    private List<PublisherDTO> preparePublisherDTOList(List<Publisher> publisherList) {
        List<PublisherDTO> publisherDTOList = new ArrayList<>();
        for (Publisher publisher : publisherList) {
            PublisherDTO publisherDTO = publisherMapper.toDTO(publisher);
            publisherDTOList.add(publisherDTO);
        }
        return publisherDTOList;
    }

    private List<Address> prepareAddress(AddressDTO addressDTO, Publisher publisher) {
        List<Address> addressList = new ArrayList<>();
        Address address = addressMapper.toEntity(addressDTO);
        address.setActive(Boolean.TRUE);
        address.setPublisher(publisher);
        addressList.add(address);
        return addressList;
    }
}
