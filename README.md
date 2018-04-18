# Load-Balancer - Short Description:
Balancing load in a Peer to Peer Systems. Single node manages jobs over the cluster and submits jobs to the systems which are free at that instant.



# Detailed Description:
  This is a cluster build with three nodes in a Peer to Peer Paradigm and Scalable to multiple number of systems depending on the computational and threads handling power of the node which submits  jobs to other nodes. One machine takes responsibility of submiting jobs to the other systems.
  Message passing technique have been used between the nodes for submiting the job and getting the results. Work is going on to replace this message passing technique with a Remote procedure calls technique to acheive robustness in the system.
  
  
# Actual Implementation:
   When a job was produced in one of the nodes in the cluster, the system first checks if the no of processes that are running by it has crossed the threshold limit(that can be tweaked in the code) and if it crosses the threshold limit it submits the job to the main system which is responsible of return the system details which is having number of running processes beneath the threshold value. When main system returns the details the system submits the job direclty communicates with the other system and get the job done.
   
   
