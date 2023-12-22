package com.xgblack.cool.gatewayimpl;




/**
 * UserProfileGatewayImpl
 *
 * @author Frank Zhang
 * @date 2020-07-02 12:32 PM
 */
//@Component
//@Slf4j
public class UserProfileGatewayImpl /*implements UserProfileGateway*/ {

    /*@Resource
    private UserProfileMapper userProfileMapper;


    public void create(UserProfile userProfile) {
        userProfileMapper.create(UserProfileConvertor.toDataObjectForCreate(userProfile));
    }

    public void update(UserProfile userProfile) {
        userProfileMapper.update(UserProfileConvertor.toDataObjectForUpdate(userProfile));
    }

    public UserProfile getByUserId(String userId) {
        UserProfileDO userProfileDO = userProfileMapper.getByUserId(userId);
        if (userProfileDO == null) {
            log.warn("There is no UserProfile for : " + userId);
            return null;
        }
        UserProfile userProfile = DomainFactory.getUserProfile();
        BeanUtils.copyProperties(userProfileDO, userProfile);
        Role role = Role.valueOf(userProfileDO.getRole());
        userProfile.setRole(role);
        userProfile.setWeight(WeightFactory.get(role));
        return userProfile;
    }*/

}
