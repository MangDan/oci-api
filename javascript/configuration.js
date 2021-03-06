// rokebi
var configs =
{
    tenancyId: 'ocid1.tenancy.oc1..aaaaaaaa63s3nhxbuf63iyjlpmgtcy577mi6heo43h3xitcdghzsyzxcaosq',
    userId: 'ocid1.user.oc1..aaaaaaaavs55culi5im6ed2zreib2zlje3jx5bsovnjhi7stmq2yxhzwemsq',
    keyFingerprint: 'f6:ca:da:c0:ed:9f:27:13:2c:9c:a0:91:8e:7b:92:ca',
    compartmentId: 'ocid1.compartment.oc1..aaaaaaaa6mftkwknfsag2erfobxfjjvsikc6osihtddczhlglzxnwxamrdlq',
    region: 'ap-seoul-1',
    RESTversion: '/20171215', //20160918
    privateKeyFile: '../../../oci_api_key.pem',
    passphrase: '',
    papertrail: {
        host: 'logs2.papertrailapp.com',
        port: 27700
    }
}

// apackrsct01
// var configs =
// {
//     tenancyId: 'ocid1.tenancy.oc1..aaaaaaaa6ma7kq3bsif76uzqidv22cajs3fpesgpqmmsgxihlbcemkklrsqa',
//     userId: 'ocid1.user.oc1..aaaaaaaalpieyqquaaneneuyiifrtfbzwcr3hqd7tqfoobwq7xr4jv5pfz3a',
//     keyFingerprint: 'f6:ca:da:c0:ed:9f:27:13:2c:9c:a0:91:8e:7b:92:ca',
//     compartmentId: 'ocid1.compartment.oc1..aaaaaaaa6mftkwknfsag2erfobxfjjvsikc6osihtddczhlglzxnwxamrdlq',
//     //region: 'ap-seoul-1',
//     region: 'us-ashburn-1',
//     RESTversion: '/20171215', //20160918
//     privateKeyFile: 'oci_api_key.pem',
//     passphrase: '',
//     papertrail: {
//         host: 'logs2.papertrailapp.com',
//         port: 27700
//     }
// }

var bucket_configs = {
    bucketName: 'test',
    namespaceName: 'cnzbqofayyug'
}

module.exports = {
    configs: configs,
    bucket_configs: bucket_configs
};
