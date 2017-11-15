```
            _        _                                            __  __              _              _
    o O O  | |      (_)     __      ___    _ _      __      ___  |  \/  |   ___    __| |   _  _     | |     ___
   o       | |__    | |    / _|    / -_)  | ' \    / _|    / -_) | |\/| |  / _ \  / _` |  | +| |    | |    / -_)
  TS__[O]  |____|  _|_|_   \__|_   \___|  |_||_|   \__|_   \___| |_|__|_|  \___/  \__,_|   \_,_|   _|_|_   \___|
 {======|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|
./o--000'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'
=================================================================================================================SharedHosting
```
LicenceModule is a project to automate the provisioning of Licences to different projects.
 
## To build and run on local with docker
1\. Make sure docker daemon is running on the system.
 
2\. Run the command :
```
docker build -t <name_of_image>:<version_tag> -f Dockerfile .
```
where,    
* <name_of_image> is the name with which docker image will be created
* <version_tag> is the tag with which docker image will be created (can be given as the version or build number)  
 
3\. Check if the  image was successfully created with command :
```
docker images
```
If your image with name <name_of_image> and tag <version_tag> given in step 2 exists you are good to go.    
 
4\. To run the docker image simply run :
```
docker run -t -p <host_port>:8080 -e ENVIRONMENT=<deployment_profile_name> --name <container_name> <name_of_image>:<version_tag>
```
where,
* <host_port> is the port number of the system on which the service will listen
* <deployment_profile_name> is the name of the profile which can be one of the following :  
        * localhost
        * staging
        * staging_remote
        * live
* <container_name> is the name that deployment container will be created with
* <name_of_image> & <version_tag> are from step 2
 
5\. The service now should be accessible on <host_port> mentioned in step 4
 
6\. that's all folks !
 
## To build and run on local with docker
1\. Follow step 1 to 3 from "To build and run on local with docker".
 
2\. In order to push to a custom docker repository, we need to first tag the image properly, hence run command :
```
docker tag <name_of_image>:<version_tag> <registry_url>/<name_in_registry>:<version_tag_in_registry>
```
where,
* <name_of_image> & <version_tag> are from step 1
* <registry_url> is the name of the custom registry that image will be pushed to i.e. "registry.dstack.tech"
* <name_in_registry> & <version_tag_in_registry> are name and tag that will appear in registry for the image
 
3\. To push the image to registry run :
```
docker push <registry_url>/<name_in_registry>:<version_tag_in_registry>
```
where,
* <registry_url> & <name_in_registry> & <version_tag_in_registry> are exactly the same as in step 2
 
4\. ssh into the server where the project is to be deployed.
 
5\. Make sure docker daemon is running on the target server.
 
6\. simply run :
```
docker run -t -p <host-port>:8080 -e ENVIRONMENT=staging --name <container_name> <registry_url>/<name_in_registry>:<version_tag_in_registry>
```
where,
* <host_port> is the port number of the system on which the service will listen
* <container_name> is the name that deployment container will be created with
* <registry_url> & <name_in_registry> & <version_tag_in_registry> are exactly the same as in step 2
 
7\. The service now should be accessible on <host_port> mentioned in step 6
 
9\. Simply follow step 6 if container needs to be deployed on any other server.
 
8\. that's all folks !
 
## To build and run on any Environment with Devstack
COMING SOON ! :)
