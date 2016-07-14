-- --------------------------------------------------------
-- 서버 버전:                        5.5.39-MariaDB - Source distribution
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- stidx 의 데이터베이스 구조 덤핑
CREATE DATABASE IF NOT EXISTS `stidx` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `stidx`;


-- 테이블 stidx의 구조를 덤프합니다. DTFGNINVTR
CREATE TABLE IF NOT EXISTS `DTFGNINVTR` (
  `GSDATE` varchar(8) NOT NULL COMMENT '자료일자',
  `GSTIME` varchar(4) NOT NULL COMMENT '자료시각',
  `STCODE` varchar(10) NOT NULL COMMENT '종목코드',
  `TD_FOREIGN` int(11) DEFAULT NULL COMMENT '외인당일',
  `TD_INVEST` int(11) DEFAULT NULL COMMENT '기관당일',
  `YD_RATE` decimal(7,3) DEFAULT NULL COMMENT '전일대비율',
  `YD_TR_RATE` decimal(7,3) DEFAULT NULL COMMENT '전일거래량 대비율',
  `STOT_FOREIGN` int(11) DEFAULT NULL COMMENT '외인잠정',
  `STOT_INVEST` int(11) DEFAULT NULL COMMENT '기관잠정',
  `YD_FOREIGN` int(11) DEFAULT NULL COMMENT '외인전일',
  `YD_INVEST` int(11) DEFAULT NULL COMMENT '기관전일',
  `RATE_RESERVE` decimal(9,3) DEFAULT NULL COMMENT '유보율(분기)',
  `EV_EBITDA` decimal(7,3) DEFAULT NULL COMMENT 'EV/EBITDA(기업가치/영업력 배수)',
  PRIMARY KEY (`GSDATE`,`GSTIME`,`STCODE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='외인기관쌍끌이잠정치매매';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 stidx의 구조를 덤프합니다. DTFGNTR
CREATE TABLE IF NOT EXISTS `DTFGNTR` (
  `GSDATE` varchar(8) NOT NULL COMMENT '자료일자',
  `GSTIME` varchar(4) NOT NULL COMMENT '자료시각',
  `STCODE` varchar(10) NOT NULL COMMENT '종목코드',
  `TD_FOREIGN` int(11) DEFAULT NULL COMMENT '외인당일',
  `TD_INVEST` int(11) DEFAULT NULL COMMENT '기관당일',
  `YD_RATE` decimal(7,3) DEFAULT NULL COMMENT '전일대비율',
  `YD_TR_RATE` decimal(7,3) DEFAULT NULL COMMENT '전일거래량 대비율',
  `STOT_FOREIGN` int(11) DEFAULT NULL COMMENT '외인잠정',
  `STOT_INVEST` int(11) DEFAULT NULL COMMENT '기관잠정',
  `YD_FOREIGN` int(11) DEFAULT NULL COMMENT '외인전일',
  `YD_INVEST` int(11) DEFAULT NULL COMMENT '기관전일',
  `RATE_RESERVE` decimal(9,3) DEFAULT NULL COMMENT '유보율(분기)',
  `EV_EBITDA` decimal(7,3) DEFAULT NULL COMMENT 'EV/EBITDA(기업가치/영업력 배수)',
  PRIMARY KEY (`GSDATE`,`GSTIME`,`STCODE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='외인잠정치매매';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 stidx의 구조를 덤프합니다. DTINVTR
CREATE TABLE IF NOT EXISTS `DTINVTR` (
  `GSDATE` varchar(8) NOT NULL COMMENT '자료일자',
  `GSTIME` varchar(4) NOT NULL COMMENT '자료시각',
  `STCODE` varchar(10) NOT NULL COMMENT '종목코드',
  `TD_FOREIGN` int(11) DEFAULT NULL COMMENT '외인당일',
  `TD_INVEST` int(11) DEFAULT NULL COMMENT '기관당일',
  `YD_RATE` decimal(7,3) DEFAULT NULL COMMENT '전일대비율',
  `YD_TR_RATE` decimal(7,3) DEFAULT NULL COMMENT '전일거래량 대비율',
  `STOT_FOREIGN` int(11) DEFAULT NULL COMMENT '외인잠정',
  `STOT_INVEST` int(11) DEFAULT NULL COMMENT '기관잠정',
  `YD_FOREIGN` int(11) DEFAULT NULL COMMENT '외인전일',
  `YD_INVEST` int(11) DEFAULT NULL COMMENT '기관전일',
  `RATE_RESERVE` decimal(9,3) DEFAULT NULL COMMENT '유보율(분기)',
  `EV_EBITDA` decimal(7,3) DEFAULT NULL COMMENT 'EV/EBITDA(기업가치/영업력 배수)',
  PRIMARY KEY (`GSDATE`,`GSTIME`,`STCODE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='기관잠정치매매';

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 stidx의 구조를 덤프합니다. STOCKCODE
CREATE TABLE IF NOT EXISTS `STOCKCODE` (
  `STCODE` varchar(10) NOT NULL COMMENT '종목코드',
  `STNAME` varchar(50) DEFAULT NULL COMMENT '종목명',
  `CATENAME` varchar(100) DEFAULT NULL COMMENT '소속업종',
  PRIMARY KEY (`STCODE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='종목코드 테이블';

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
