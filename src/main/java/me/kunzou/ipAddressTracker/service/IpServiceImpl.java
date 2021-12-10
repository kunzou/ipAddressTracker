package me.kunzou.ipAddressTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IpServiceImpl implements IpService {

  private static final String IPIFY_URL = "https://api.ipify.org";
  private RestTemplate restTemplate;

  public IpServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public String getIp() {
    return restTemplate.getForEntity(IPIFY_URL, String.class).getBody();
  }
}
