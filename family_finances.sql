USE [master]
GO
/****** Object:  Database [family_finances]    Script Date: 2019/7/5 12:47:49 ******/
CREATE DATABASE [family_finances]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'family_finances', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\family_finances.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'family_finances_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\family_finances_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [family_finances] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [family_finances].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [family_finances] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [family_finances] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [family_finances] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [family_finances] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [family_finances] SET ARITHABORT OFF 
GO
ALTER DATABASE [family_finances] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [family_finances] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [family_finances] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [family_finances] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [family_finances] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [family_finances] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [family_finances] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [family_finances] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [family_finances] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [family_finances] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [family_finances] SET  DISABLE_BROKER 
GO
ALTER DATABASE [family_finances] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [family_finances] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [family_finances] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [family_finances] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [family_finances] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [family_finances] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [family_finances] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [family_finances] SET RECOVERY FULL 
GO
ALTER DATABASE [family_finances] SET  MULTI_USER 
GO
ALTER DATABASE [family_finances] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [family_finances] SET DB_CHAINING OFF 
GO
ALTER DATABASE [family_finances] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [family_finances] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'family_finances', N'ON'
GO
USE [family_finances]
GO
/****** Object:  User [ua]    Script Date: 2019/7/5 12:47:49 ******/
CREATE USER [ua] FOR LOGIN [user1] WITH DEFAULT_SCHEMA=[ua]
GO
/****** Object:  DatabaseRole [ra]    Script Date: 2019/7/5 12:47:49 ******/
CREATE ROLE [ra]
GO
ALTER ROLE [ra] ADD MEMBER [ua]
GO
/****** Object:  Schema [ua]    Script Date: 2019/7/5 12:47:49 ******/
CREATE SCHEMA [ua]
GO
/****** Object:  Table [dbo].[expend]    Script Date: 2019/7/5 12:47:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[expend](
	[eno] [int] NOT NULL,
	[name] [varchar](20) NOT NULL,
	[category] [char](30) NULL,
	[expend_day] [date] NULL,
	[expend_money] [bigint] NOT NULL,
	[remark] [varchar](50) NULL,
 CONSTRAINT [PK__expend__D9507B87BBE927FE] PRIMARY KEY CLUSTERED 
(
	[eno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[family]    Script Date: 2019/7/5 12:47:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[family](
	[call] [char](10) NULL,
	[name] [varchar](20) NOT NULL,
	[birthday] [date] NULL,
	[work] [char](10) NULL,
 CONSTRAINT [PK__family__72E12F1AABA9D6AC] PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[income]    Script Date: 2019/7/5 12:47:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[income](
	[ino] [int] NOT NULL,
	[name] [varchar](20) NOT NULL,
	[category] [char](30) NULL,
	[income_day] [date] NULL,
	[income_money] [bigint] NOT NULL,
	[remark] [varchar](50) NULL,
 CONSTRAINT [PK__income__DC50F6DDBDF31505] PRIMARY KEY CLUSTERED 
(
	[ino] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[users]    Script Date: 2019/7/5 12:47:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[users](
	[users] [char](15) NOT NULL,
	[password] [char](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[users] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  View [dbo].[inex]    Script Date: 2019/7/5 12:47:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create view [dbo].[inex]
as
    select income.name 姓名,income_money 收入金额 ,income_day 收入时间,expend_money 支出金额 ,expend_day 支出时间
	 from income,expend 
	where income.name=expend.name
GO
/****** Object:  Index [ex]    Script Date: 2019/7/5 12:47:49 ******/
CREATE NONCLUSTERED INDEX [ex] ON [dbo].[expend]
(
	[eno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [fx]    Script Date: 2019/7/5 12:47:49 ******/
CREATE NONCLUSTERED INDEX [fx] ON [dbo].[family]
(
	[birthday] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [ix]    Script Date: 2019/7/5 12:47:49 ******/
CREATE NONCLUSTERED INDEX [ix] ON [dbo].[income]
(
	[ino] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [family_finances] SET  READ_WRITE 
GO
