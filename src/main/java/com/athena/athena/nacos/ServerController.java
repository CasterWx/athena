package com.athena.athena.nacos;

import com.athena.athena.common.HResult;
import com.athena.athena.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/nacos")
public class ServerController {

    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * 服务发现
     * */
    @GetMapping(value = "/search")
    public Result<List<ServiceInstance>> get() {
        Result<List<ServiceInstance>> result = new Result<>(HResult.H_OK);
        List<ServiceInstance> serviceInstances = servicesFinding();

        if (serviceInstances==null){
            result.setStatus(HResult.H_NO_DATA);
            return result;
        }

        result.setData(serviceInstances);
        return result;
    }

    public List<ServiceInstance> servicesFinding() {
        return discoveryClient.getInstances("athena-client");
    }

    /**
     * 负载均衡
     *
     * */

}
