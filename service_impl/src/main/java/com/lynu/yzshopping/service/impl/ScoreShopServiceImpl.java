package com.lynu.yzshopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.lynu.yzshopping.dao.ScoreDao;
import com.lynu.yzshopping.dao.ScoreShopDao;
import com.lynu.yzshopping.dao.UserGoodMappingDao;
import com.lynu.yzshopping.mybatis.entity.Score;
import com.lynu.yzshopping.mybatis.entity.ScoreShop;
import com.lynu.yzshopping.mybatis.entity.UserGoodMapping;
import com.lynu.yzshopping.service.ScoreService;
import com.lynu.yzshopping.service.ScoreShopService;
import com.lynu.yzshopping.service.UserService;
import com.lynu.yzshopping.service.constants.YZConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "scoreShopService")
public class ScoreShopServiceImpl implements ScoreShopService {
    @Autowired
    ScoreShopDao scoreShopDao;
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    UserGoodMappingDao userGoodMappingDao;
    @Autowired
    ScoreService scoreService;

    @Autowired
    ScoreShopService scoreShopService;

    @Override
    public List<ScoreShop> selectByConditionMap(Map<String, Object> map) {
        return scoreShopDao.selectByConditionMap(map);
    }

    @Override
    public List<ScoreShop> selectByScore(Map<String, Object> paramMap) {

        String pageStr = (String) paramMap.get("pageStr");
        String pageSizeStr = (String) paramMap.get("pageSizeStr");
        String score = (String) paramMap.get("score");
        //根据 "-"进行分割
        String[] split = score.split("-");
        Integer scoreLow = Integer.parseInt(split[0]);
        Integer scoreUp = Integer.parseInt(split[1]);


        Map<String, Object> map = new HashMap<>();
        map.put("scoreLow", scoreLow);
        map.put("scoreUp", scoreUp);
        return scoreShopDao.selectByScore(map);
    }

    @Override
    public List<ScoreShop> selectAllByScoreAsc(Map<String, Object> map) {
        return scoreShopDao.selectAllByScoreAsc(map);
    }

    //收藏、兑换商品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String SaveOrExchangeShop(Map<String, Object> map) {
        Integer userId = (Integer) map.get("userId");
        Integer goodsId = (Integer) map.get("goodsId");
        Integer status = (Integer) map.get("status");

        if (status == YZConstants.EXCHANGE_STATUS) {//兑换商品
            try {
                //获取到用户拥有的积分
                List<Score> scoreList = scoreService.selectByConditionMap(map);
                Integer scoreTotal = Integer.parseInt(scoreList.get(scoreList.size() - 1).getScoreTotal());

                Map<String,Object> sMap=new HashMap<>();
                sMap.put("id",goodsId);
                //获取到兑换商品所需积分
                List<ScoreShop> scoreShopList = scoreShopService.selectByConditionMap(sMap);
                ScoreShop scoreShop = scoreShopList.get(0);
                Integer gPrice = Integer.parseInt(scoreShop.getgPrice());

                //进行比较
                if (scoreTotal < gPrice) {
                    return "积分不足";
                }
                //兑换开始
                //1.修改积分表用户积分
                Score score = new Score();
                score.setUserId(userId);
                score.setScoreTotal((scoreTotal - gPrice) + "");
                score.setOperationNumber(gPrice + "");
                score.setOperationSign(YZConstants.MINUS_SIGN);
                score.setOperationDescribe("商品兑换");
                score.setCreateTime(new Date());
                scoreService.insert(score);
                //2.修改商品数量
                Integer gNumber = Integer.parseInt(scoreShop.getgNumber());
                if (gNumber <= 0) {
                    return "商品已售罄";
                }
                scoreShop.setgNumber((gNumber - 1) + "");
                scoreShopDao.updateByPrimaryKey(scoreShop);
                //3.加入用户-商品映射表
                UserGoodMapping userGoodMapping = new UserGoodMapping();
                userGoodMapping.setUserId(userId);
                userGoodMapping.setGoodsId(goodsId);
                userGoodMapping.setStatus(status + "");
                userGoodMapping.setCreateTime(new Date());
                userGoodMappingDao.insert(userGoodMapping);
                return "兑换成功！";
            } catch (Exception e) {
                return "错误";
            }

        }

        //点击收藏的操作
        if (status == YZConstants.SAVE_STATUS) {

            return "收藏成功";
        }
        return "错误";
    }
}
