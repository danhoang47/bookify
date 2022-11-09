USE [master]
GO
/****** Object:  Database [bookify]    Script Date: 11/9/2022 10:30:05 PM ******/
CREATE DATABASE [bookify]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'bookify', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MAYAO\MSSQL\DATA\bookify.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'bookify_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MAYAO\MSSQL\DATA\bookify_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [bookify] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [bookify].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [bookify] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [bookify] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [bookify] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [bookify] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [bookify] SET ARITHABORT OFF 
GO
ALTER DATABASE [bookify] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [bookify] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [bookify] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [bookify] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [bookify] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [bookify] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [bookify] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [bookify] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [bookify] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [bookify] SET  ENABLE_BROKER 
GO
ALTER DATABASE [bookify] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [bookify] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [bookify] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [bookify] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [bookify] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [bookify] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [bookify] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [bookify] SET RECOVERY FULL 
GO
ALTER DATABASE [bookify] SET  MULTI_USER 
GO
ALTER DATABASE [bookify] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [bookify] SET DB_CHAINING OFF 
GO
ALTER DATABASE [bookify] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [bookify] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [bookify] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [bookify] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'bookify', N'ON'
GO
ALTER DATABASE [bookify] SET QUERY_STORE = OFF
GO
USE [bookify]
GO
/****** Object:  Table [dbo].[Booking]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Booking](
	[booking_id] [varchar](50) NOT NULL,
	[user_id] [varchar](50) NULL,
	[check_in] [date] NULL,
	[check_out] [date] NULL,
	[adult] [int] NULL,
	[child] [int] NULL,
	[infants] [int] NULL,
	[pets] [int] NULL,
	[room_id] [varchar](50) NOT NULL,
	[status] [int] NULL,
	[bookAt] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[booking_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Hotel]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hotel](
	[hotel_id] [varchar](50) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[hoteltype_id] [varchar](50) NOT NULL,
	[hotel_name] [nvarchar](50) NULL,
	[background_image] [varchar](150) NULL,
	[is_verified] [bit] NULL,
	[description] [nvarchar](300) NOT NULL,
	[country] [nvarchar](100) NULL,
	[district] [nvarchar](100) NULL,
	[city] [nvarchar](100) NULL,
	[address] [nvarchar](100) NULL,
	[isAllowPet] [bit] NULL,
	[isHasCamera] [bit] NULL,
	[checkin] [nvarchar](10) NULL,
	[checkout] [nvarchar](10) NULL,
	[closing] [nvarchar](10) NULL,
	[opening] [nvarchar](10) NULL,
	[signAt] [date] NULL,
 CONSTRAINT [PK__Hotel__45FE7E264DFD887D] PRIMARY KEY CLUSTERED 
(
	[hotel_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Room]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Room](
	[room_id] [varchar](50) NOT NULL,
	[hotel_id] [varchar](50) NULL,
	[room_type_id] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[room_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[bookingDateRange]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[bookingDateRange] as
select ht.hotel_id, bk.check_in, bk.check_out, rm.room_id from hotel as ht, room as rm, booking as bk 
where ht.hotel_id=rm.hotel_id and bk.room_id=rm.room_id 
GO
/****** Object:  Table [dbo].[Image]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image](
	[image_id] [varchar](50) NOT NULL,
	[hotel_id] [varchar](50) NULL,
	[image] [nvarchar](300) NULL,
	[type] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[image_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[homeHotelsImage]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[homeHotelsImage] as
select ht.hotel_id, img.image from hotel as ht, Image as img where ht.hotel_id=img.hotel_id group by ht.hotel_id, img.image
GO
/****** Object:  Table [dbo].[Review]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Review](
	[review_id] [varchar](50) NOT NULL,
	[hotel_id] [varchar](50) NULL,
	[user_id] [varchar](50) NULL,
	[content] [nvarchar](300) NULL,
	[source_id] [int] NULL,
	[communication_point] [int] NULL,
	[accuracy_point] [int] NULL,
	[location_point] [int] NULL,
	[value_point] [int] NULL,
	[create_at] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[review_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RoomType]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoomType](
	[id] [varchar](50) NOT NULL,
	[price] [int] NULL,
	[bed_type] [nvarchar](50) NULL,
	[bed_number] [int] NULL,
	[bathroom_type] [nvarchar](50) NULL,
	[bathroom_number] [int] NULL,
	[is_private_bathroom] [bit] NULL,
	[guests_id] [nvarchar](50) NULL,
	[number_of_guests] [int] NULL,
	[number_of_room] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[getAllHotelBasicInfo]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[getAllHotelBasicInfo] as
select ht.hotel_id, ht.hotel_name, ht.hoteltype_id, ht.background_image, ht.country, ht.city, ht.district, ht.address, AVG(rt.price) as average_price,
(select isnull((select AVG(rv.communication_point + rv.accuracy_point + rv.location_point + rv.value_point)/4 
from review as rv where rv.hotel_id=ht.hotel_id), 0)) as rating
from Hotel as ht, Room as rm, RoomType as rt
where ht.hotel_id=rm.hotel_id and rm.room_type_id=rt.id and ht.is_verified=1
group by ht.hotel_id, ht.hotel_name, ht.city, ht.district, ht.address, ht.country, ht.hoteltype_id, ht.background_image
GO
/****** Object:  View [dbo].[AdvancedFilter]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[AdvancedFilter] as
select ht.hotel_id, ht.hotel_name, ht.hoteltype_id, ht.background_image, ht.country, ht.city, ht.district, ht.address, AVG(rt.price) as average_price,
(select isnull((select AVG(rv.communication_point + rv.accuracy_point + rv.location_point + rv.value_point)/4 
from review as rv where rv.hotel_id=ht.hotel_id), 0)) as rating,
AVG(rt.number_of_room) as number_of_room,
AVG(rt.bed_number) as bed_number,
AVG(rt.bathroom_number) as bath_number
from Hotel as ht, Room as rm, RoomType as rt
where ht.hotel_id=rm.hotel_id and rm.room_type_id=rt.id and ht.is_verified=1
group by ht.hotel_id, ht.hotel_name, ht.city, ht.district, ht.address, ht.country, ht.hoteltype_id, ht.background_image
GO
/****** Object:  View [dbo].[SearchAdvance]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[SearchAdvance] as
select ht.hotel_id, ht.hotel_name, ht.hoteltype_id, ht.background_image, ht.country, ht.city, ht.district, ht.address, 
AVG(rt.price) as average_price,
(select isnull((select AVG(rv.communication_point + rv.accuracy_point + rv.location_point + rv.value_point)/4 
from review as rv where rv.hotel_id=ht.hotel_id), 0)) as rating,
AVG(rt.number_of_guests) as number_of_guest
from Hotel as ht, Room as rm, RoomType as rt
where ht.hotel_id=rm.hotel_id and rm.room_type_id=rt.id and ht.is_verified=1
group by ht.hotel_id, ht.hotel_name, ht.city, ht.district, ht.address, ht.country, ht.hoteltype_id, ht.background_image
GO
/****** Object:  View [dbo].[getHotel]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[getHotel] as
SELECT        hotel_id, user_id, hoteltype_id, hotel_name, background_image, is_verified, description, country, district, city, address, isAllowPet, isHasCamera, checkin, checkout, closing, opening, signAt,
                             (SELECT        ISNULL
                                                             ((SELECT        AVG(communication_point + accuracy_point + location_point + value_point) / 4 AS Expr1
                                                                 FROM            dbo.Review AS rv
                                                                 WHERE        (hotel_id = ht.hotel_id)), 0) AS Expr1) AS rating
FROM            dbo.Hotel AS ht
GO
/****** Object:  View [dbo].[dateRangeBookingMerged]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[dateRangeBookingMerged] as
with bookingDateRangeMerge as (
    Select p1.check_in, p1.check_out, p1.hotel_id, p1.room_id
    from
        bookingDateRange p1
            left join
        bookingDateRange p2
            on
                p1.check_in = DATEADD(day,1,p2.check_out)
				and p1.hotel_id=p2.hotel_id
    where
        p2.check_in is null
    union all
    select p1.check_in,p2.check_out, p2.hotel_id, p2.room_id
    from
        bookingDateRangeMerge p1
            inner join
        bookingDateRange p2
            on
                p1.check_out = DATEADD(day,-1,p2.check_in)
				and p1.hotel_id=p2.hotel_id
)
select hotel_id, check_in,MAX(check_out) as check_out, room_id
from bookingDateRangeMerge
group by check_in, hotel_id, room_id
GO
/****** Object:  Table [dbo].[Amenity]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Amenity](
	[amenity_id] [varchar](50) NOT NULL,
	[amenity_name] [nvarchar](100) NULL,
	[icon] [varchar](50) NULL,
	[type_id] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[amenity_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Amenity_type]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Amenity_type](
	[amenity_type_id] [nvarchar](50) NOT NULL,
	[amenity_type_name] [nvarchar](50) NULL,
	[amenity_type_number] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[amenity_type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BankingAccount]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BankingAccount](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[bank_number] [varchar](100) NULL,
	[amount] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BookingRoom]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookingRoom](
	[booking_id] [varchar](50) NULL,
	[user_id] [varchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bookmark]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bookmark](
	[bookmark_id] [varchar](50) NOT NULL,
	[user_id] [varchar](50) NULL,
	[hotel_id] [varchar](50) NULL,
	[bookmark_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[bookmark_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Guests]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Guests](
	[guests_id] [varchar](50) NULL,
	[adult] [int] NULL,
	[child] [int] NULL,
	[infant] [int] NULL,
	[pet] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HotelAmenities]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HotelAmenities](
	[hotel_amenities_id] [varchar](50) NOT NULL,
	[amenity_id] [varchar](50) NULL,
	[hotel_id] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[hotel_amenities_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hotelType]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hotelType](
	[hoteltype_id] [varchar](50) NOT NULL,
	[hoteltype] [varchar](50) NULL,
	[icon] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[hoteltype_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hotelView]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hotelView](
	[hotelViewId] [int] IDENTITY(1,1) NOT NULL,
	[hotel_id] [varchar](50) NULL,
	[Jan] [int] NULL,
	[Feb] [int] NULL,
	[Mar] [int] NULL,
	[Apr] [int] NULL,
	[May] [int] NULL,
	[Jun] [int] NULL,
	[Jul] [int] NULL,
	[Aug] [int] NULL,
	[Sep] [int] NULL,
	[Oct] [int] NULL,
	[Nov] [int] NULL,
	[Dec] [int] NULL,
	[YearTime] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[hotelViewId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notification]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notification](
	[notify_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [varchar](50) NULL,
	[source_id] [varchar](50) NULL,
	[notify_type] [int] NULL,
	[notify_date] [datetime] NULL,
	[is_read] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[notify_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Report]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Report](
	[report_id] [varchar](50) NOT NULL,
	[hotel_id] [varchar](50) NULL,
	[user_id] [varchar](50) NULL,
	[title] [nvarchar](50) NULL,
	[content] [nvarchar](300) NULL,
	[report_date] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[report_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RuleDetail]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RuleDetail](
	[rule_id] [varchar](50) NOT NULL,
	[hotel_id] [varchar](50) NULL,
	[context] [nvarchar](100) NULL,
	[icon] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[rule_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transact]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transact](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [varchar](50) NULL,
	[createdAt] [datetime] NULL,
	[amount] [int] NULL,
	[type] [int] NULL,
	[booking_id] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[userDetail]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[userDetail](
	[user_id] [varchar](50) NOT NULL,
	[username] [varchar](50) NULL,
	[user_password] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[phone] [varchar](50) NULL,
	[name] [varchar](50) NULL,
	[avatar] [varchar](100) NULL,
	[role] [smallint] NULL,
	[ggid] [varchar](50) NULL,
	[whislist_id] [varchar](50) NULL,
	[self_description] [nvarchar](300) NULL,
	[salt] [varchar](200) NULL,
	[subname] [nvarchar](100) NULL,
	[dob] [date] NULL,
	[signAt] [date] NULL,
	[banking_account_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'Pool', N'faSwimmingPool', N'230198c8-317c-4f60-8a60-78e6ab002963')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'0d9fd954-78d7-49ae-a465-043be9c1d07c', N'Wifi', N'faWifi', N'230198c8-317c-4f60-8a60-78e6ab002963')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'Exercise equipment', N'faDumbbell', N'30550c0c-0985-43ae-890e-9b8979a6e6c6')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'BBQ grill', N'faFire', N'b84a89d6-7831-4c72-8a03-8fba95d0d318')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'Fire pit', N'faCampground', N'b84a89d6-7831-4c72-8a03-8fba95d0d318')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'81995780-b20c-4b4b-9219-8187ce831359', N'Indoor fireplace', N'faFireBurner', N'e5db1bd1-4708-44e5-aaf0-089a85ea239b')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'Hot tub', N'faHotTub', N'30550c0c-0985-43ae-890e-9b8979a6e6c6')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'99fd19a0-818b-412f-a6e2-fc13f69dff58', N'Outdoor dining area', N'faChair', N'b84a89d6-7831-4c72-8a03-8fba95d0d318')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'Air conditioning', N'faAirFreshener', N'230198c8-317c-4f60-8a60-78e6ab002963')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'eba60c2c-60e7-4bf3-9376-b55e0028d4c1', N'Patio', N'faCouch', N'b84a89d6-7831-4c72-8a03-8fba95d0d318')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'fe384dc7-b633-4ed8-a86e-60a2fb4705f0', N'Pool table', N'faBraille', N'f7bedf8c-7c2c-43e8-a8ec-fc327eb0f520')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'new-313409d3-3c5b-4956-8d7c-3d529f94731e', N'cam trai', N'faPencil', N'230198c8-317c-4f60-8a60-78e6ab002963')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'new-3d18e756-ae0c-4bfb-a624-awjdaw8dy9', N'Boi Loi', N'faPencil', N'230198c8-317c-4f60-8a60-78e6ab002963')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'new-3d18e756-ae0c-4bfb-a624-e5a9a357a54d', N'Danh nhau', N'faPencil', N'230198c8-317c-4f60-8a60-78e6ab002963')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'new-77c8c771-638e-4cab-8d60-a216573b17ba', N'Lặn san hô', N'faPencil', N'30550c0c-0985-43ae-890e-9b8979a6e6c6')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'new-dd77f923-9ffc-432c-8f22-0dd28637c6ee', N'Ngắm biển', N'faPencil', N'b84a89d6-7831-4c72-8a03-8fba95d0d318')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'new-e69c0520-d1ba-43b7-86da-770961e978a9', N'Bep', N'faPencil', N'230198c8-317c-4f60-8a60-78e6ab002963')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon], [type_id]) VALUES (N'new-fc60d550-43f4-41f3-814f-c67d48fc71d0', N'Ban cung', N'faPencil', N'30550c0c-0985-43ae-890e-9b8979a6e6c6')
GO
INSERT [dbo].[Amenity_type] ([amenity_type_id], [amenity_type_name], [amenity_type_number]) VALUES (N'230198c8-317c-4f60-8a60-78e6ab002963', N'Gia đình', 5)
INSERT [dbo].[Amenity_type] ([amenity_type_id], [amenity_type_name], [amenity_type_number]) VALUES (N'30550c0c-0985-43ae-890e-9b8979a6e6c6', N'Dịch vụ', 3)
INSERT [dbo].[Amenity_type] ([amenity_type_id], [amenity_type_name], [amenity_type_number]) VALUES (N'b84a89d6-7831-4c72-8a03-8fba95d0d318', N'Ngoài trời', 5)
INSERT [dbo].[Amenity_type] ([amenity_type_id], [amenity_type_name], [amenity_type_number]) VALUES (N'e5db1bd1-4708-44e5-aaf0-089a85ea239b', N'Đò dùng nấu bếp và ăn uống', 1)
INSERT [dbo].[Amenity_type] ([amenity_type_id], [amenity_type_name], [amenity_type_number]) VALUES (N'f7bedf8c-7c2c-43e8-a8ec-fc327eb0f520', N'Giải trí', 3)
GO
SET IDENTITY_INSERT [dbo].[BankingAccount] ON 

INSERT [dbo].[BankingAccount] ([id], [bank_number], [amount]) VALUES (1, N'asdc-dawd-2312d-dasd', 11107)
INSERT [dbo].[BankingAccount] ([id], [bank_number], [amount]) VALUES (2, N'asdc-dawd-2312d-dase', 6963)
INSERT [dbo].[BankingAccount] ([id], [bank_number], [amount]) VALUES (3, N'asdc-c01c-abwc2-cbawi', 9999)
INSERT [dbo].[BankingAccount] ([id], [bank_number], [amount]) VALUES (4, N'asdc-dawd-2312d-eeee', 10000)
INSERT [dbo].[BankingAccount] ([id], [bank_number], [amount]) VALUES (5, N'1111-1111-1111-1111', 10000)
INSERT [dbo].[BankingAccount] ([id], [bank_number], [amount]) VALUES (6, N'2222-2222-2222-2222', 10000)
SET IDENTITY_INSERT [dbo].[BankingAccount] OFF
GO
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'02589164-8b25-4ba7-9253-7dafac512888', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-25' AS Date), CAST(N'2022-11-30' AS Date), 2, 0, 0, 0, N'3efc9306-f5cb-435f-a53b-4299edaec91c', 1, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'03d68040-5dd2-11ed-9b6a-0242ac120002', N'123', CAST(N'2022-11-05' AS Date), CAST(N'2022-11-07' AS Date), 2, 0, 0, 0, N'886aeea0-73fb-4fca-bff5-c127419b375b', 1, CAST(N'2022-11-03' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'05626770-b285-4326-80f9-8e6b5162d886', N'123', CAST(N'2022-11-07' AS Date), CAST(N'2022-11-13' AS Date), 2, 0, 0, 0, N'2ecb71cc-d224-4e15-a245-87b45b1c1628', 0, CAST(N'2022-11-05' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'10c09e93-278e-489b-93a2-153c5c3a6ac1', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-10' AS Date), 2, 1, 0, 0, N'bad5224c-6bed-4a1d-aa5d-e645ffa20c4f', 1, CAST(N'2022-11-07' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'1cc40572-5e69-11ed-9b6a-0242ac120002', N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-10' AS Date), CAST(N'2022-11-12' AS Date), 2, 1, 0, 0, N'2ecb71cc-d224-4e15-a245-87b45b1c1628', 1, CAST(N'2022-10-09' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'2096880a-5e69-11ed-9b6a-0242ac120002', N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-11' AS Date), CAST(N'2022-11-13' AS Date), 2, 1, 0, 1, N'10c2cba4-8555-4809-bef3-3eadc8c61530', 1, CAST(N'2022-11-09' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'2166304a-49e1-4608-87f1-24fb755e9860', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-16' AS Date), CAST(N'2022-11-18' AS Date), 2, 2, 0, 0, N'bbe412b7-5211-49d3-91f6-f7ed8373bc0f', 0, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'3da29cc5-bf38-4252-9975-5c3b0f21f843', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-11' AS Date), 3, 0, 0, 0, N'04971c93-b213-4821-a602-606cd4c0440c', 1, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'47a2c812-c673-4d19-83a5-edaf2677867f', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-24' AS Date), CAST(N'2022-11-26' AS Date), 2, 1, 0, 1, N'bad5224c-6bed-4a1d-aa5d-e645ffa20c4f', 1, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'4c7ed5f9-3f98-4392-a7d3-5651d7ab178b', N'123', CAST(N'2022-11-18' AS Date), CAST(N'2022-11-20' AS Date), 2, 0, 0, 0, N'2706c071-76ea-4645-9741-03b166bad469', 1, CAST(N'2022-11-01' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'51c5a3f0-e314-49e1-a344-1930b474923d', N'123', CAST(N'2022-11-06' AS Date), CAST(N'2022-11-16' AS Date), 2, 0, 0, 0, N'7cede936-11cc-4198-9aee-ab57d418aedb', 1, CAST(N'2022-11-03' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'5303759a-2cc6-45d7-8c38-7f2fa5831b07', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-10' AS Date), 2, 0, 0, 0, N'3efc9306-f5cb-435f-a53b-4299edaec91c', 1, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'5e0ca394-aef1-41ea-b257-926af43b84db', N'123', CAST(N'2022-11-11' AS Date), CAST(N'2022-11-15' AS Date), 2, 0, 0, 0, N'2706c071-76ea-4645-9741-03b166bad469', 1, CAST(N'2022-11-01' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'5e4380a6-ab64-4859-9076-1fe52a1eb370', N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-11' AS Date), 2, 0, 0, 0, N'44d9db80-9a4e-4bc8-83cc-10bc77de5bde', 2, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'68161ecf-1eb3-4f69-8beb-05cca853817b', N'123', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-12' AS Date), 2, 0, 0, 0, N'f3c98d0a-c264-4ae3-9c2f-5ce6b22e5ffc', 0, CAST(N'2022-11-01' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'706236c4-520a-4178-8fe0-5b9c52271be9', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-17' AS Date), CAST(N'2022-11-21' AS Date), 2, 1, 0, 0, N'184d56e6-d3c2-4436-8ce4-0a114604c34f', 0, CAST(N'2022-11-07' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'7f21d276-5c0e-4518-ae5a-dd19e723c2a3', N'123', CAST(N'2022-11-05' AS Date), CAST(N'2022-11-12' AS Date), 2, 0, 0, 0, N'57adeb48-b17c-43bc-80c5-baf365587e17', 0, CAST(N'2022-11-03' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'86d8d68f-c5e1-4884-add8-bdcd53f806fe', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-10' AS Date), 2, 1, 0, 0, N'0d778063-8d32-4cd1-a05b-6940992cef90', 0, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'953b7e1d-0bff-4354-8d49-42c4ae3f44b7', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-09' AS Date), CAST(N'2022-11-12' AS Date), 2, 1, 0, 0, N'bad5224c-6bed-4a1d-aa5d-e645ffa20c4f', 1, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'96281f04-5def-11ed-9b6a-0242ac120002', N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-01' AS Date), CAST(N'2022-11-05' AS Date), 2, 1, 0, 0, N'2ecb71cc-d224-4e15-a245-87b45b1c1628', 1, CAST(N'2022-10-30' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'a14a8cf2-4f4e-40bf-918a-f3b25ac17c82', N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-11' AS Date), 2, 0, 0, 0, N'44d9db80-9a4e-4bc8-83cc-10bc77de5bde', 2, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'acqcq-12f08-awd-awd-awdawd12d1d12d', N'123', CAST(N'2022-11-18' AS Date), CAST(N'2022-11-20' AS Date), 2, 0, 0, 0, N'886aeea0-73fb-4fca-bff5-c127419b375b', 1, CAST(N'2022-11-05' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'acqcq-12f08-awd-awd-zxveg4wgw42412', N'123', CAST(N'2022-11-10' AS Date), CAST(N'2022-11-16' AS Date), 2, 0, 0, 0, N'886aeea0-73fb-4fca-bff5-c127419b375b', 1, CAST(N'2022-11-05' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'asqwf19gcv-awf4-12f1-qgwd1892dg912', N'123', CAST(N'2022-11-09' AS Date), CAST(N'2022-11-20' AS Date), 2, 0, 0, 0, N'858346d9-9cf1-4b6b-8c93-2a966f1bc62d', 1, CAST(N'2022-11-05' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'b2f57a85-559f-4a97-bb21-f719c1e2d9f6', N'123', CAST(N'2022-11-07' AS Date), CAST(N'2022-11-10' AS Date), 2, 0, 0, 0, N'2706c071-76ea-4645-9741-03b166bad469', 0, CAST(N'2022-11-05' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'c2dc968d-a272-421f-808c-6c12bf32e4ee', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-09' AS Date), CAST(N'2022-11-11' AS Date), 2, 0, 0, 0, N'184d56e6-d3c2-4436-8ce4-0a114604c34f', 0, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'c45f55ed-219f-4623-adf5-07b3e54dc034', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-11' AS Date), CAST(N'2022-11-13' AS Date), 1, 1, 0, 0, N'bad5224c-6bed-4a1d-aa5d-e645ffa20c4f', 1, CAST(N'2022-11-07' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'da9cf487-e3b5-4dbd-93f3-c93379fe68ae', N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-11' AS Date), 2, 0, 0, 0, N'44d9db80-9a4e-4bc8-83cc-10bc77de5bde', 2, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'dc3f6e1a-b42a-49af-ac15-a02953b565a2', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-11' AS Date), 5, 0, 0, 0, N'04971c93-b213-4821-a602-606cd4c0440c', 1, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'dfwawdaw-d125-asc2-80f9-d12d12dada2', N'123', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-13' AS Date), 2, 0, 0, 0, N'543c42fc-85fd-4b3b-b798-ee34c8cf67da', 1, CAST(N'2022-11-05' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'dfwawdaw-d125-asc2-80f9-sdadwd12cb', N'123', CAST(N'2022-11-16' AS Date), CAST(N'2022-11-20' AS Date), 2, 0, 0, 0, N'543c42fc-85fd-4b3b-b798-ee34c8cf67da', 1, CAST(N'2022-11-05' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'e4db1aac-5def-11ed-9b6a-0242ac120002', N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-06' AS Date), CAST(N'2022-11-10' AS Date), 2, 1, 0, 1, N'10c2cba4-8555-4809-bef3-3eadc8c61530', 1, CAST(N'2022-11-04' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'ee4c0d72-b30f-49da-b2fd-ba08a03c7ea4', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08' AS Date), CAST(N'2022-11-11' AS Date), 3, 0, 0, 0, N'04971c93-b213-4821-a602-606cd4c0440c', 1, CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'f987380a-5dd1-11ed-9b6a-0242ac120002', N'123', CAST(N'2022-11-01' AS Date), CAST(N'2022-11-03' AS Date), 2, 0, 0, 0, N'543c42fc-85fd-4b3b-b798-ee34c8cf67da', 1, CAST(N'2022-11-01' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'fd823928-5dd1-11ed-9b6a-0242ac120002', N'123', CAST(N'2022-11-02' AS Date), CAST(N'2022-11-04' AS Date), 2, 0, 0, 0, N'886aeea0-73fb-4fca-bff5-c127419b375b', 1, CAST(N'2022-11-02' AS Date))
INSERT [dbo].[Booking] ([booking_id], [user_id], [check_in], [check_out], [adult], [child], [infants], [pets], [room_id], [status], [bookAt]) VALUES (N'w1f132f12f-b285-80f9-awf13g214g24g', N'123', CAST(N'2022-11-07' AS Date), CAST(N'2022-11-15' AS Date), 2, 0, 0, 0, N'8bdb64e3-e921-4da4-9d18-d4fc287995fc', 1, CAST(N'2022-11-05' AS Date))
GO
INSERT [dbo].[Bookmark] ([bookmark_id], [user_id], [hotel_id], [bookmark_date]) VALUES (N'480dbd2a-5771-4413-b4f1-83e91622e04b', N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'cfcd8503-dda9-41d8-8368-759b018557d7', CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Bookmark] ([bookmark_id], [user_id], [hotel_id], [bookmark_date]) VALUES (N'8b154432-27ba-42cc-956c-2b3bf11af98b', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Bookmark] ([bookmark_id], [user_id], [hotel_id], [bookmark_date]) VALUES (N'8bd2552c-5f97-4e42-a257-749360d91919', N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Bookmark] ([bookmark_id], [user_id], [hotel_id], [bookmark_date]) VALUES (N'cd2d7134-faaa-494d-abf7-a2dc5bc7ee3f', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'72f79650-45bd-4705-90a2-7fc5839e3e28', CAST(N'2022-11-08' AS Date))
GO
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'91bb91b4-adcb-45ba-a0bf-1669a4d5878f', N'Marriot 2', N'http://localhost:8080/bookify/images/hotels/types-of-resorts-July282020-1-min.jpg.webp', 1, N'awuidh awidh awuidh awiudh aiuhd aiuwdh aiuwhd aidhaiudh iuahd aiuwdh auiwdh iuwadh aiuwdh aiuwdh iauwhdiauwdh aiuwdh aiuwdhaiwudhaiwudh iauwdh aiuwdh aiuwdh aiwuhd iawudh aiw d', N'Việt Nam', N'Thành phố Đà Nẵng', N'Hai Chau', N'12 Bach Dang', 1, 1, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-11-06' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'71057869-312d-4695-a586-76bf1ccb762e', N'afbd51cf-8353-4303-ab47-f0785dc9c34f', N'Novotel', N'http://localhost:8080/bookify/images/hotels/Hotel-from-above-1.webp', 0, N'awhd aiudh awidh awiudhawiud aiwuwich awiuc wicbwiucb awicbawi ucbawiucbawuicbaiwcbiwucb awuicbvbcba wchawic awicbiaw ciawc aiwucaw cia cbiawc ', N'Việt Nam', N'Tỉnh Thừa Thiên Huế', N'Hue', N'180 Bach Dang', 1, 1, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-11-06' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e1078832-9c59-44a0-8d5e-39cd60c44822', N'afbd51cf-8353-4303-ab47-f0785dc9c34f', N'Đào tiên ', N'http://localhost:8080/bookify/images/hotels/hilton-moorea-lagoon-resort-spa-moorea-french-poly--110160-1.webp', 1, N'Khách sạn tuyệt vời, bảo mật tốt, iahdiuaw hdiawuh dwaiujaw đă awdiu awdiawh diuah duiawdh iauwdh awid a widhawid hawid aiudauichawic awi awicb awic ăcc', N'Việt Nam', N'Thành phố Đà Nẵng', N'Sơn Trà', N'123 Thanh Hóa', 1, 1, N'10:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'b955c796-027c-4e11-92ff-1bb942a102c8', N'afbd51cf-8353-4303-ab47-f0785dc9c34f', N'Mường Thanh Huế', N'http://localhost:8080/bookify/images/hotels/black.jpg', 0, N'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ', N'Việt Nam', N'Tỉnh Thừa Thiên Huế', N'Thành phố Huế', N'28 Bà Triệu', 0, 0, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-09-15' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'b955c796-027c-4e11-92ff-1bb942a102c8', N'afbd51cf-8353-4303-ab47-f0785dc9c34f', N'Profilosto', N'http://localhost:8080/bookify/images/hotels/380569812.jpg', 1, N'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.', N'Việt Nam', N'Thành phố Đà Nẵng', N'Hue', N'28 Hung Vuong', 0, 0, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-08-27' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'5403d2ea-0c94-44b8-81f7-7b4986051570', N'Vinhome ', N'http://localhost:8080/bookify/images/hotels/Hotel-from-above-1.webp', 1, N'dasdasdaosdasoidjasiodjaiosdjaoidjaosadisjda', N'Việt Nam', N'Tỉnh Thừa Thiên Huế', N'Thanh pho Hue', N'28 Hung Vuong', 1, 1, N'10:30', N'10:30', N'22:30', N'12:30', CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'bf6f9891-2faf-4d63-954c-827b07511ce9', N'7f7076c1-c7a1-4111-a01f-597160769e30', N'39ed093c-720a-4fc7-aca5-70b15d338da8', N'khach san asdasdasd', N'http://localhost:8080/bookify/images/hotels/types-of-resorts-July282020-1-min.jpg.webp', 1, N'iudh awid hawid hawiudhawiudhaid awic awc', N'Việt Nam', N'Hue', N'Hue', N'28 Hung Vuong', 0, 0, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-11-08' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'cfcd8503-dda9-41d8-8368-759b018557d7', N'8f850c39-086f-4c15-aede-ab7e00d26dd4', N'afbd51cf-8353-4303-ab47-f0785dc9c34f', N'Khach san DE160552', N'http://localhost:8080/bookify/images/hotels/blossom-hotel-houston.jpg', 1, N'aowd awdhj iadhawidh awidha idhawid aoid awih awciobawic baic auic awucbawc bawicbawcba cbaw cba baoc bawicb awiocb awicbaw icbaowi cbawiocb awc awcbiaw cbawicb aiwcb awicb ', N'Việt Nam', N'Tỉnh Thừa Thiên Huế', N'Hue', N'28 Hung Vuong', 1, 1, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-11-06' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'b955c796-027c-4e11-92ff-1bb942a102c8', N'39ed093c-720a-4fc7-aca5-70b15d338da8', N'Khách sạn Le Duc 2', N'http://localhost:8080/bookify/images/hotels/196860396_109306291389442_3251558626855161876_n.jpg', 1, N'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.', N'Việt Nam', N'Tỉnh Thừa Thiên Huế', N'Lang Co', N'28 Hung Vuong', 0, 0, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-10-13' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'b955c796-027c-4e11-92ff-1bb942a102c8', N'39ed093c-720a-4fc7-aca5-70b15d338da8', N'Khách sạn của Đức', N'http://localhost:8080/bookify/images/hotels/download.jpg', 0, N'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit', N'Việt Nam', N'Thành phố Hà Nội', N'Quan 1', N'28 Hung Vuong', 0, 0, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-10-21' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'f282783f-1d57-4321-91d1-938fcc2906f4', N'b955c796-027c-4e11-92ff-1bb942a102c8', N'9d5050f0-3d2f-4c80-a6f5-d82a215dda21', N'Khach san dang ky', N'http://localhost:8080/bookify/images/hotels/775065_15101615100036903321.jpg', 0, N'akdhaskdjhaskcnawinaw ichawiu hawidha wuich awiuchawiuchchzejklcbaicuhqa icuan awch awicbnawuichbcbahcb zhcbzjhcb auwbcyuiqwbcahbcaucbaicb uacbiabc aiwcbaicbaw c', N'Việt Nam', N'Tỉnh Thừa Thiên Huế', N'Hue', N'28 Hung Vuong', 1, 1, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-11-06' AS Date))
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [background_image], [is_verified], [description], [country], [district], [city], [address], [isAllowPet], [isHasCamera], [checkin], [checkout], [closing], [opening], [signAt]) VALUES (N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'b955c796-027c-4e11-92ff-1bb942a102c8', N'91bb91b4-adcb-45ba-a0bf-1669a4d5878f', N'AirBnb', N'http://localhost:8080/bookify/images/hotels/revato-275450-12292942-924181.jpg', 1, N'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga.', N'Việt Nam', N'Thành phố Đà Nẵng', N'Hai Chau', N'89 Bach Dang', 1, 1, N'12:30', N'12:30', N'12:30', N'12:30', CAST(N'2022-11-01' AS Date))
GO
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'016d6a58-9c45-46c6-9ce3-9a1b7e874520', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'f98320c3-235a-4cb7-a0a8-eda132b0e545')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'0191f897-d2a7-4448-aeca-586353eeae08', N'fe384dc7-b633-4ed8-a86e-60a2fb4705f0', N'ee40b247-2a92-4466-ba72-4a95ed32eabd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'030920e0-bb7a-4d81-b5ac-d8eead269406', N'eba60c2c-60e7-4bf3-9376-b55e0028d4c1', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'0b98eefc-d2d8-47f2-8bbf-0f00cb6f4906', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'72f79650-45bd-4705-90a2-7fc5839e3e28')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'0e961b80-b62b-4382-a1d5-9070f102c5eb', N'81995780-b20c-4b4b-9219-8187ce831359', N'df28f4c5-8907-4bcf-a9b7-3849cb860815')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'14f1cf3a-1fc9-4e3d-b4a5-7956a074b093', N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'cfcd8503-dda9-41d8-8368-759b018557d7')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'17b8f2fe-11ee-41e2-9624-1a72833692e7', N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'17c6efcf-2d67-422c-9dea-53849fc466ea', N'81995780-b20c-4b4b-9219-8187ce831359', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'1cb8fbdb-d755-4595-a26a-dea3f5a45617', N'99fd19a0-818b-412f-a6e2-fc13f69dff58', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'1db5c768-ba9e-4c28-8394-23dad8832dbe', N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'f98320c3-235a-4cb7-a0a8-eda132b0e545')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'1f490fbd-41b6-454c-ba9a-342649edac73', N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'1f51e0c9-969b-4520-9949-d595379141ac', N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'cfcd8503-dda9-41d8-8368-759b018557d7')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'26f8cca3-ceaa-477c-b9a2-ade86aee0df0', N'81995780-b20c-4b4b-9219-8187ce831359', N'72f79650-45bd-4705-90a2-7fc5839e3e28')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'290f42fe-d3c2-4dee-a2e7-4885fd6b482a', N'eba60c2c-60e7-4bf3-9376-b55e0028d4c1', N'f98320c3-235a-4cb7-a0a8-eda132b0e545')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'2ad548a6-a155-4d7f-8587-c1cc09b98186', N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'321d5e05-d610-44bc-bec8-d32f3876f0d5', N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'99801e69-dd9e-42f0-ab32-318936bb87c4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'370407b8-a6ae-4dc0-bdef-3d75ab4f40a8', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'3a779194-a82c-49b9-8063-234baa95d473', N'99fd19a0-818b-412f-a6e2-fc13f69dff58', N'ee40b247-2a92-4466-ba72-4a95ed32eabd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'3c9f426e-a074-43c2-8b54-2a8003833713', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'3f0aa9fb-c81d-4bce-bba1-67b5f51758f0', N'fe384dc7-b633-4ed8-a86e-60a2fb4705f0', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'406ace49-7af1-47dc-bdd3-cf6e6b7f15d9', N'0d9fd954-78d7-49ae-a465-043be9c1d07c', N'df28f4c5-8907-4bcf-a9b7-3849cb860815')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'414b7515-b436-43ec-951c-28d4945b4c2c', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'99801e69-dd9e-42f0-ab32-318936bb87c4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'417a3a74-b1b7-48a8-a2ab-ac3229dbe56a', N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'5c30100b-1395-4bef-a74e-191e4c047dbe', N'99fd19a0-818b-412f-a6e2-fc13f69dff58', N'f98320c3-235a-4cb7-a0a8-eda132b0e545')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'5f541c50-babb-4e50-bcf7-e7610c44167d', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'99801e69-dd9e-42f0-ab32-318936bb87c4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'66619cd8-4428-4372-b6de-0fa80a7f9209', N'fe384dc7-b633-4ed8-a86e-60a2fb4705f0', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'69252926-87d2-4fe9-a6a0-9bda7c398606', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'f98320c3-235a-4cb7-a0a8-eda132b0e545')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'6b049d31-6f1a-4485-bdba-367bebcda039', N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'ee40b247-2a92-4466-ba72-4a95ed32eabd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'6c86df0f-6b89-4b9a-aa32-af5ff6755c2c', N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'6d29be75-790d-417a-8d7f-e460d663185d', N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'bf6f9891-2faf-4d63-954c-827b07511ce9')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'76e85d7c-d844-44d1-9e40-4ecde0db23d3', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'cfcd8503-dda9-41d8-8368-759b018557d7')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'779f5ff6-3fb4-4b7a-a098-75732a980b6d', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'79887960-ae32-44bb-b563-90086d4028e1', N'81995780-b20c-4b4b-9219-8187ce831359', N'99801e69-dd9e-42f0-ab32-318936bb87c4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'7f9e0b99-3132-4c92-8ff8-e843b1194349', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'f282783f-1d57-4321-91d1-938fcc2906f4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'84068805-6bf6-4d46-9cc8-004f82bc41da', N'81995780-b20c-4b4b-9219-8187ce831359', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'8594b7fe-168a-4454-bce2-76b905e714d9', N'0d9fd954-78d7-49ae-a465-043be9c1d07c', N'ee40b247-2a92-4466-ba72-4a95ed32eabd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'8a8c98b3-de48-4b18-b974-acaec3379526', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'cfcd8503-dda9-41d8-8368-759b018557d7')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'94e263e9-ea29-4749-b50d-88b5c935d847', N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'951375d6-11dd-4d05-8c9c-83ea51f998c5', N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'96a864c5-0d3a-4ef5-b539-205cca96f41f', N'0d9fd954-78d7-49ae-a465-043be9c1d07c', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'96b6638f-89f7-4abf-b03e-3ace05ce7956', N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'9da8e217-ac92-4fde-b931-7b5dbeaaec2f', N'eba60c2c-60e7-4bf3-9376-b55e0028d4c1', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'a389e406-cf91-437f-bf75-ed61c1ee9099', N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'bf6f9891-2faf-4d63-954c-827b07511ce9')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'a96fb90a-dda1-47c1-8e8e-a0e8e569d836', N'81995780-b20c-4b4b-9219-8187ce831359', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'ac9b82da-2e1d-417c-8b37-d41ede6dfb3f', N'81995780-b20c-4b4b-9219-8187ce831359', N'f98320c3-235a-4cb7-a0a8-eda132b0e545')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'ae897637-2e48-4204-a846-a96208d9d9c2', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'af0673eb-19f0-4c64-855b-e4d750129a31', N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'b107a492-5666-43c3-8176-8bcd485965be', N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'f282783f-1d57-4321-91d1-938fcc2906f4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'b6808579-53c3-4429-9a73-021d67012e50', N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'df28f4c5-8907-4bcf-a9b7-3849cb860815')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'baaf1760-6b4c-4eca-8f26-265dc9b9ac60', N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'f282783f-1d57-4321-91d1-938fcc2906f4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'bb5ff055-b149-44b7-86ca-22673caa92cc', N'new-77c8c771-638e-4cab-8d60-a216573b17ba', N'cfcd8503-dda9-41d8-8368-759b018557d7')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'bd585018-0e6d-435e-ac25-b07075f6247f', N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'ee40b247-2a92-4466-ba72-4a95ed32eabd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'bd6684b7-4143-43d9-a8c7-e14721c60a18', N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'c31b0f86-f6a5-488f-9e06-d372a62e1779', N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'c60f128f-3a18-46e6-9f1a-8c6a95ddcc11', N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'c953400e-40ca-4ccc-a76b-3cb6c92a6348', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'd14e4a0b-e2cd-4d16-b5b3-5e15d8a4c80f', N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'72f79650-45bd-4705-90a2-7fc5839e3e28')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'd45594dd-c4b9-4ae1-a896-e5dd57583560', N'99fd19a0-818b-412f-a6e2-fc13f69dff58', N'bf6f9891-2faf-4d63-954c-827b07511ce9')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'd5661138-4d00-435e-b62c-e87ef5f724fb', N'new-dd77f923-9ffc-432c-8f22-0dd28637c6ee', N'f282783f-1d57-4321-91d1-938fcc2906f4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'd8d4ca84-d3a5-481f-b64e-ae0b297014ce', N'0d9fd954-78d7-49ae-a465-043be9c1d07c', N'99801e69-dd9e-42f0-ab32-318936bb87c4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'de475019-cd24-47de-a5f0-a6e9f4f54540', N'new-e69c0520-d1ba-43b7-86da-770961e978a9', N'99801e69-dd9e-42f0-ab32-318936bb87c4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'df551168-6541-4e4a-9992-8cc1351dd8c3', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'e2b5c283-074e-419f-b56d-d06f81524420', N'99fd19a0-818b-412f-a6e2-fc13f69dff58', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'e31d0dcd-9cbd-4932-8d8f-974ff63f1477', N'new-77c8c771-638e-4cab-8d60-a216573b17ba', N'df28f4c5-8907-4bcf-a9b7-3849cb860815')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'e51b93c8-ed77-4fe5-a0a2-620fa58a497d', N'fe384dc7-b633-4ed8-a86e-60a2fb4705f0', N'f282783f-1d57-4321-91d1-938fcc2906f4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'e75ef8db-a677-454f-8bf4-0283f1fe466e', N'eba60c2c-60e7-4bf3-9376-b55e0028d4c1', N'bf6f9891-2faf-4d63-954c-827b07511ce9')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'e767cb83-929b-4626-b9e4-69137a209a18', N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'e9c5d66d-15ea-43d8-b1ff-1107fc76efe4', N'0d9fd954-78d7-49ae-a465-043be9c1d07c', N'f98320c3-235a-4cb7-a0a8-eda132b0e545')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'ebfd25c7-73ad-4a11-8caf-e90d84e5fe76', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'efbbbb42-cfa9-489e-a3fb-d148ceb56e12', N'0d9fd954-78d7-49ae-a465-043be9c1d07c', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'f0d207ef-5e55-41d3-93cf-61a60fe98aa6', N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'f98320c3-235a-4cb7-a0a8-eda132b0e545')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'f4fb72d6-c894-444d-b0bb-098d1b352352', N'eba60c2c-60e7-4bf3-9376-b55e0028d4c1', N'ee40b247-2a92-4466-ba72-4a95ed32eabd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'f5d54c73-b86e-4094-b032-7a2b859f4f4d', N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'f8cb6936-7070-49ef-ac1f-24ad95d96281', N'fe384dc7-b633-4ed8-a86e-60a2fb4705f0', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'f990d1e0-93d4-43f0-9700-dc82afb7182b', N'99fd19a0-818b-412f-a6e2-fc13f69dff58', N'df28f4c5-8907-4bcf-a9b7-3849cb860815')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'fc810092-dc49-46b4-bbcb-5b03bc555d93', N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'fcd15c39-f2ab-4b33-9a00-073302100169', N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'f282783f-1d57-4321-91d1-938fcc2906f4')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'fcf1dd88-9810-4f6f-923e-388b23ed3de5', N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [hotel_id]) VALUES (N'ffafb0fa-812b-4fde-b535-ea6cec972ae5', N'new-3d18e756-ae0c-4bfb-a624-awjdaw8dy9', N'cfcd8503-dda9-41d8-8368-759b018557d7')
GO
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'314ec1bd-204b-4e8e-8b3a-098b4cc110fa', N'House', N'image2.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'39ed093c-720a-4fc7-aca5-70b15d338da8', N'Resort', N'image5.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'5403d2ea-0c94-44b8-81f7-7b4986051570', N'Bed and breakfast', N'image5.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'91bb91b4-adcb-45ba-a0bf-1669a4d5878f', N'Apartment', N'image1.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'9d5050f0-3d2f-4c80-a6f5-d82a215dda21', N'Unique space', N'image4.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'afbd51cf-8353-4303-ab47-f0785dc9c34f', N'Hotel', N'image3.png')
GO
SET IDENTITY_INSERT [dbo].[hotelView] ON 

INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (1, N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 64, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (2, N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (3, N'df28f4c5-8907-4bcf-a9b7-3849cb860815', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (4, N'ee40b247-2a92-4466-ba72-4a95ed32eabd', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (5, N'f282783f-1d57-4321-91d1-938fcc2906f4', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (6, N'f98320c3-235a-4cb7-a0a8-eda132b0e545', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (7, N'cfcd8503-dda9-41d8-8368-759b018557d7', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 44, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (8, N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (9, N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 57, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (10, N'72f79650-45bd-4705-90a2-7fc5839e3e28', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (11, N'99801e69-dd9e-42f0-ab32-318936bb87c4', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 2022)
INSERT [dbo].[hotelView] ([hotelViewId], [hotel_id], [Jan], [Feb], [Mar], [Apr], [May], [Jun], [Jul], [Aug], [Sep], [Oct], [Nov], [Dec], [YearTime]) VALUES (12, N'bf6f9891-2faf-4d63-954c-827b07511ce9', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2022)
SET IDENTITY_INSERT [dbo].[hotelView] OFF
GO
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'00a94381-954b-4937-9f75-db4679310416', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'http://localhost:8080/bookify/images/hotels/download.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'011f107e-2671-48b9-b743-3aae67e647dc', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'http://localhost:8080/bookify/images/hotels/9636.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'014cbda4-bfcb-4fc0-a307-76dd1b1b47cb', N'cfcd8503-dda9-41d8-8368-759b018557d7', N'http://localhost:8080/bookify/images/hotels/types-of-resorts-July282020-1-min.jpg.webp', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'046d7a08-516c-4c70-bb47-010fe6c6bed1', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'http://localhost:8080/bookify/images/hotels/esperanza-at-night.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'08ffff28-cb6b-4d22-95c1-5d38bb3c35b7', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'http://localhost:8080/bookify/images/hotels/Beaches-Turks-Caicos-Overview.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'0e51ecc4-0253-4d36-b2c1-d0346fd6074e', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'http://localhost:8080/bookify/images/hotels/types-of-resorts-July282020-1-min.jpg.webp', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'0eefa54e-1b0a-467d-b710-e04ada703e96', N'f282783f-1d57-4321-91d1-938fcc2906f4', N'http://localhost:8080/bookify/images/hotels/16256-145921-f72742573_3xl.webp', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'15645b96-cc3b-4987-bad1-757e8c2d1ff7', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'http://localhost:8080/bookify/images/hotels/blossom-hotel-houston.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'1f09ff21-556e-4fd8-9ea3-710f86ba3a32', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'http://localhost:8080/bookify/images/hotels/_V5A9588.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'1f9684a4-c7e9-471b-9c5b-1fec9beda29d', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'http://localhost:8080/bookify/images/hotels/3003.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'20ca42df-d73e-49e7-8d65-02b2f817d6e2', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'http://localhost:8080/bookify/images/hotels/Hotel-from-above-1.webp', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'20f9efe3-8505-4243-b9ac-1e78fb0a3875', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'http://localhost:8080/bookify/images/hotels/Screenshot_20221006_033818.png', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'27b48c70-8e2e-4ab1-8d31-6f462af235d0', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'http://localhost:8080/bookify/images/hotels/revato-275450-12292942-924181.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'34f6b6b0-7dd1-46a1-825c-0bded50f95e8', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'http://localhost:8080/bookify/images/hotels/esperanza-at-night.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'38cc1d2b-fbef-446a-8240-3f88be24cb61', N'f282783f-1d57-4321-91d1-938fcc2906f4', N'http://localhost:8080/bookify/images/hotels/blossom-hotel-houston.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'3daa2437-c34c-4d8c-8e62-a41d9e48431b', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'http://localhost:8080/bookify/images/hotels/arbisoftimages-128085-_RHP0098-image.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'4e26fb72-c1bc-4cae-a757-6abb2f7cbae3', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'http://localhost:8080/bookify/images/hotels/types-of-resorts-July282020-1-min.jpg.webp', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'52194bef-1af1-4ea1-9f43-34c810124853', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'http://localhost:8080/bookify/images/hotels/black4.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'56221595-451d-43d3-a827-6027af548a1d', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'http://localhost:8080/bookify/images/hotels/Hotel-from-above-1.webp', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'5c3e7cc3-a78c-4505-8f4d-f8156745ce21', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'http://localhost:8080/bookify/images/hotels/Main-pool-and-beach-at-Atmosphere-Resorts-Spa.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'77a7d4aa-741b-40d8-be6f-d2a28bc9613d', N'cfcd8503-dda9-41d8-8368-759b018557d7', N'http://localhost:8080/bookify/images/hotels/hilton-moorea-lagoon-resort-spa-moorea-french-poly--110160-1.webp', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'7d68f3a7-eff8-42fe-8fad-95609b06877b', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'http://localhost:8080/bookify/images/hotels/arbisoftimages-128085-_RHP0098-image.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'83f34455-611b-4f2c-a34d-d40c14ced4c6', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'http://localhost:8080/bookify/images/hotels/download.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'87133adb-03d2-4197-a3ed-20379a545c18', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'http://localhost:8080/bookify/images/hotels/Download this wallpaper _ oceanofwallpapers_com.png', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'96d5996a-954b-460e-ab42-69e147652ea8', N'bf6f9891-2faf-4d63-954c-827b07511ce9', N'http://localhost:8080/bookify/images/hotels/types-of-resorts-July282020-1-min.jpg.webp', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'a334a822-01c2-4c0a-acd7-452cd687beaa', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'http://localhost:8080/bookify/images/hotels/hilton-moorea-lagoon-resort-spa-moorea-french-poly--110160-1.webp', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'a5fd24a6-6dda-4828-84fe-8119b81fb1f2', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'http://localhost:8080/bookify/images/hotels/hilton-moorea-lagoon-resort-spa-moorea-french-poly--110160-1.webp', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'a9ca2d3e-3978-47ec-9528-d3dbb0f09e6b', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'http://localhost:8080/bookify/images/hotels/blossom-hotel-houston.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'b11b9aac-30b6-451d-9a4e-eed9e918908f', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'http://localhost:8080/bookify/images/hotels/Main-pool-and-beach-at-Atmosphere-Resorts-Spa.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'b2413a3d-1410-4068-92e6-108cf3c9cebc', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'http://localhost:8080/bookify/images/hotels/Screenshot_20221008_101550.png', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'b4e4df1f-67b3-4042-96b2-ab77aa781384', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'http://localhost:8080/bookify/images/hotels/types-of-resorts-July282020-1-min.jpg.webp', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'b7223a65-6106-45d5-aa21-5b4782a22cc9', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'http://localhost:8080/bookify/images/hotels/download.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'b8b06e35-283a-4787-b08d-d98027a5a0b0', N'bf6f9891-2faf-4d63-954c-827b07511ce9', N'http://localhost:8080/bookify/images/hotels/revato-275450-12292942-924181.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'c307d3ed-2c83-4004-8843-f93667b5864b', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'http://localhost:8080/bookify/images/hotels/9636.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'caeb6dde-66fa-4772-9d97-a098c4897b11', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'http://localhost:8080/bookify/images/hotels/Beaches-Turks-Caicos-Overview.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'cd325d3a-7a8e-4a8d-acf4-fad9019fb954', N'cfcd8503-dda9-41d8-8368-759b018557d7', N'http://localhost:8080/bookify/images/hotels/flamingo-dai-lai-forest-restaurant.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'cf82520f-e87a-489c-a060-ecc3328a4429', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'http://localhost:8080/bookify/images/hotels/esperanza-at-night.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'cfadf6b5-b2a1-46ca-b44d-9ab439af9a56', N'bf6f9891-2faf-4d63-954c-827b07511ce9', N'http://localhost:8080/bookify/images/hotels/775065_15101615100036903321.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'd07fd426-7160-497b-a920-030fecf5bae0', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'http://localhost:8080/bookify/images/hotels/download.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'd3126101-9bb0-4a08-9ea8-bb809e3a1c0e', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'http://localhost:8080/bookify/images/hotels/black3.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'd360e7c7-c50a-40a9-a937-3fb35890f9d6', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'http://localhost:8080/bookify/images/hotels/download.jpg', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'dc721853-3b62-490d-8b06-77ad8173bc46', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'http://localhost:8080/bookify/images/hotels/types-of-resorts-July282020-1-min.jpg.webp', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'de67b23a-d809-44b0-b2de-1873badf33b3', N'f282783f-1d57-4321-91d1-938fcc2906f4', N'http://localhost:8080/bookify/images/hotels/arbisoftimages-128085-_RHP0098-image.jpg', 0)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'e1c00015-f8b6-4cbb-abe5-b399279cfa50', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'http://localhost:8080/bookify/images/hotels/Lofi Girl on Twitter.png', 1)
INSERT [dbo].[Image] ([image_id], [hotel_id], [image], [type]) VALUES (N'e98fd82c-093a-4c0d-b9ab-ab25b69693c8', N'bf6f9891-2faf-4d63-954c-827b07511ce9', N'http://localhost:8080/bookify/images/hotels/Main-pool-and-beach-at-Atmosphere-Resorts-Spa.jpg', 0)
GO
SET IDENTITY_INSERT [dbo].[Notification] ON 

INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (1, N'b955c796-027c-4e11-92ff-1bb942a102c8', N'706236c4-520a-4178-8fe0-5b9c52271be9', 0, CAST(N'2022-11-07T18:59:24.063' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (2, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'10c09e93-278e-489b-93a2-153c5c3a6ac1', 5, CAST(N'2022-11-07T19:23:24.417' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (3, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'10c09e93-278e-489b-93a2-153c5c3a6ac1', 3, CAST(N'2022-11-07T19:26:36.607' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (4, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'c45f55ed-219f-4623-adf5-07b3e54dc034', 5, CAST(N'2022-11-07T19:38:01.927' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (5, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'c45f55ed-219f-4623-adf5-07b3e54dc034', 3, CAST(N'2022-11-07T19:38:20.517' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (6, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'0df1919c-b697-45b4-91e0-8043d2cfda4c', 2, CAST(N'2022-11-07T23:47:46.023' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (7, N'b955c796-027c-4e11-92ff-1bb942a102c8', N'c2dc968d-a272-421f-808c-6c12bf32e4ee', 0, CAST(N'2022-11-08T00:11:37.443' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (8, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'c6272106-5ffa-4ff9-84a3-411bf0ef497f', 2, CAST(N'2022-11-08T00:28:48.113' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (9, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'50183625-182b-42bd-865f-f5fad9a70f3a', 2, CAST(N'2022-11-08T00:54:05.690' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (10, N'b955c796-027c-4e11-92ff-1bb942a102c8', N'2166304a-49e1-4608-87f1-24fb755e9860', 0, CAST(N'2022-11-08T04:30:34.840' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (11, N'e1078832-9c59-44a0-8d5e-39cd60c44822', N'02589164-8b25-4ba7-9253-7dafac512888', 5, CAST(N'2022-11-08T09:29:35.877' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (12, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'02589164-8b25-4ba7-9253-7dafac512888', 3, CAST(N'2022-11-08T09:30:15.937' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (13, N'e1078832-9c59-44a0-8d5e-39cd60c44822', N'5303759a-2cc6-45d7-8c38-7f2fa5831b07', 5, CAST(N'2022-11-08T10:34:10.453' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (14, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'0e7b7007-ccfb-4a5d-87b7-eb45d368b6a0', 2, CAST(N'2022-11-08T10:34:53.363' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (15, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'5303759a-2cc6-45d7-8c38-7f2fa5831b07', 3, CAST(N'2022-11-08T10:37:35.110' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (16, N'b955c796-027c-4e11-92ff-1bb942a102c8', N'86d8d68f-c5e1-4884-add8-bdcd53f806fe', 0, CAST(N'2022-11-08T10:51:58.003' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (17, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'331394f3-8645-46ce-917a-bc79dfe7d161', 2, CAST(N'2022-11-08T10:53:29.827' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (18, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'953b7e1d-0bff-4354-8d49-42c4ae3f44b7', 5, CAST(N'2022-11-08T10:59:19.010' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (19, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'47a2c812-c673-4d19-83a5-edaf2677867f', 5, CAST(N'2022-11-08T10:59:34.760' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (20, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'47a2c812-c673-4d19-83a5-edaf2677867f', 3, CAST(N'2022-11-08T11:00:07.040' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (21, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'953b7e1d-0bff-4354-8d49-42c4ae3f44b7', 3, CAST(N'2022-11-08T11:00:15.280' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (22, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'dc3f6e1a-b42a-49af-ac15-a02953b565a2', 5, CAST(N'2022-11-08T11:06:50.830' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (23, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'3da29cc5-bf38-4252-9975-5c3b0f21f843', 5, CAST(N'2022-11-08T11:07:04.000' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (24, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'ee4c0d72-b30f-49da-b2fd-ba08a03c7ea4', 5, CAST(N'2022-11-08T11:07:14.843' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (25, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'3da29cc5-bf38-4252-9975-5c3b0f21f843', 5, CAST(N'2022-11-08T11:07:52.080' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (26, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'dc3f6e1a-b42a-49af-ac15-a02953b565a2', 5, CAST(N'2022-11-08T11:08:03.117' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (27, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'ee4c0d72-b30f-49da-b2fd-ba08a03c7ea4', 5, CAST(N'2022-11-08T11:08:05.583' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (28, N'7f7076c1-c7a1-4111-a01f-597160769e30', N'da9cf487-e3b5-4dbd-93f3-c93379fe68ae', 6, CAST(N'2022-11-08T11:15:38.993' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (29, N'7f7076c1-c7a1-4111-a01f-597160769e30', N'5e4380a6-ab64-4859-9076-1fe52a1eb370', 6, CAST(N'2022-11-08T11:15:50.133' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (30, N'7f7076c1-c7a1-4111-a01f-597160769e30', N'a14a8cf2-4f4e-40bf-918a-f3b25ac17c82', 6, CAST(N'2022-11-08T11:15:55.590' AS DateTime), 1)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (31, N'7f7076c1-c7a1-4111-a01f-597160769e30', N'a14a8cf2-4f4e-40bf-918a-f3b25ac17c82', 6, CAST(N'2022-11-08T11:17:23.160' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (32, N'7f7076c1-c7a1-4111-a01f-597160769e30', N'5e4380a6-ab64-4859-9076-1fe52a1eb370', 6, CAST(N'2022-11-08T11:17:25.040' AS DateTime), 0)
INSERT [dbo].[Notification] ([notify_id], [user_id], [source_id], [notify_type], [notify_date], [is_read]) VALUES (33, N'7f7076c1-c7a1-4111-a01f-597160769e30', N'da9cf487-e3b5-4dbd-93f3-c93379fe68ae', 6, CAST(N'2022-11-08T11:17:26.533' AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[Notification] OFF
GO
INSERT [dbo].[Report] ([report_id], [hotel_id], [user_id], [title], [content], [report_date]) VALUES (N'155395f4-88fa-4415-9b7c-5e4720b10851', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'Khách sạn vệ sinh chưa tốt', N'Tôi đã gặp rắc rối với vấn đề vệ sinh của khách sạn', CAST(N'2022-11-07T17:37:55.337' AS DateTime))
INSERT [dbo].[Report] ([report_id], [hotel_id], [user_id], [title], [content], [report_date]) VALUES (N'9ad6286d-d08d-4397-8704-asdasdawd12e1', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'9ad6286d-d08d-4397-8704-b8a1aff07309', N'Khong sach se', N'qwhiuqwdh qwiudhqid hqiwudhq dhqwiuwuic qwibcqiuwcbqwic qwucbqwcoqwc ybca ubcua cbawyuc abwoicua wcoaywcb aowucb awcbaw cbaocbacb zocb', CAST(N'2022-11-04T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[Review] ([review_id], [hotel_id], [user_id], [content], [source_id], [communication_point], [accuracy_point], [location_point], [value_point], [create_at]) VALUES (N'0df1919c-b697-45b4-91e0-8043d2cfda4c', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'Khách sạn này rất tuyệt, tôi cực kì hài lòng về trải nghiệm của bản thân ở đây', 0, 5, 4, 4, 4, CAST(N'2022-11-07T23:47:46.013' AS DateTime))
INSERT [dbo].[Review] ([review_id], [hotel_id], [user_id], [content], [source_id], [communication_point], [accuracy_point], [location_point], [value_point], [create_at]) VALUES (N'0e7b7007-ccfb-4a5d-87b7-eb45d368b6a0', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'Khach san that la tuyet voi', 0, 3, 4, 3, 2, CAST(N'2022-11-08T10:34:53.357' AS DateTime))
INSERT [dbo].[Review] ([review_id], [hotel_id], [user_id], [content], [source_id], [communication_point], [accuracy_point], [location_point], [value_point], [create_at]) VALUES (N'331394f3-8645-46ce-917a-bc79dfe7d161', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'Khach san tuyet voi', 0, 2, 3, 2, 4, CAST(N'2022-11-08T10:53:29.783' AS DateTime))
INSERT [dbo].[Review] ([review_id], [hotel_id], [user_id], [content], [source_id], [communication_point], [accuracy_point], [location_point], [value_point], [create_at]) VALUES (N'50183625-182b-42bd-865f-f5fad9a70f3a', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'Khach san tuyet voi', 0, 5, 5, 5, 5, CAST(N'2022-11-08T00:54:05.677' AS DateTime))
INSERT [dbo].[Review] ([review_id], [hotel_id], [user_id], [content], [source_id], [communication_point], [accuracy_point], [location_point], [value_point], [create_at]) VALUES (N'5d216baa-5dc4-11ed-9b6a-0242ac120002', N'cfcd8503-dda9-41d8-8368-759b018557d7', N'9ad6286d-d08d-4397-8704-b8a1aff07309', N'Day la 1 khach san tuyet voi, toi se ghe lai vao lan sau', 0, 5, 4, 5, 3, CAST(N'2022-11-06T18:17:53.233' AS DateTime))
INSERT [dbo].[Review] ([review_id], [hotel_id], [user_id], [content], [source_id], [communication_point], [accuracy_point], [location_point], [value_point], [create_at]) VALUES (N'c6272106-5ffa-4ff9-84a3-411bf0ef497f', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'Khach san ok', 0, 3, 2, 2, 3, CAST(N'2022-11-08T00:28:48.107' AS DateTime))
GO
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'01d80561-7ffc-4a8e-aef6-516b066c13ee', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'04971c93-b213-4821-a602-606cd4c0440c', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'057f5cac-fb27-4c4e-9063-d5b2fa809c9f', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'e158df8e-63fb-49a1-9ec4-ce4ccf309ee2')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'08cf7fe0-0cfc-4b98-a916-88a676b0ac5b', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'0993abf0-ccb9-4fd7-a3f9-269e9bb0aaae', N'f282783f-1d57-4321-91d1-938fcc2906f4', N'1b9febcc-0a1f-40aa-addc-1d83976acb63')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'0a3a1dbd-5740-4a84-8716-93a1a6584b85', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'e158df8e-63fb-49a1-9ec4-ce4ccf309ee2')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'0b18fefa-be0a-4d1e-86df-05c14c63b864', N'f282783f-1d57-4321-91d1-938fcc2906f4', N'1b9febcc-0a1f-40aa-addc-1d83976acb63')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'0d778063-8d32-4cd1-a05b-6940992cef90', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'4e1e9c0a-332a-46a1-b739-ce6a731a514d')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'0d905fa5-d210-4ac9-9460-d1b543fecabd', N'f282783f-1d57-4321-91d1-938fcc2906f4', N'1b9febcc-0a1f-40aa-addc-1d83976acb63')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'0e2c894a-c530-44a9-bab0-52fb0ecbc0f8', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'10c2cba4-8555-4809-bef3-3eadc8c61530', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'184d56e6-d3c2-4436-8ce4-0a114604c34f', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'e158df8e-63fb-49a1-9ec4-ce4ccf309ee2')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'1894d4b4-5eb1-4aa5-b72c-2d96a150c9c4', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'1922828d-48c1-4b87-80ae-9f692a508503', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'2564cdc6-7e83-4010-85c6-b88350abf8c9', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'2669000d-e233-4859-9fd1-ef0efc3ba4fd', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'f5f18b5c-dfa6-4e0d-a480-258cd1a9d7f5')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'2706c071-76ea-4645-9741-03b166bad469', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'369a8ae8-ae1c-4b51-92a3-3395495db301')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'279d496c-69e6-4eed-b6ec-6ecc945ad515', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'2b9538e9-eb1b-469f-bcd4-5d2ea42312db', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'2bfa4537-89c1-41c9-95d6-85d0f745108e', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'2df0b5b4-b117-4b83-87a1-25b32ece04fe', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'4e1e9c0a-332a-46a1-b739-ce6a731a514d')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'2dfac552-610b-454a-8233-54e185f59cf6', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'2ecb71cc-d224-4e15-a245-87b45b1c1628', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'369a8ae8-ae1c-4b51-92a3-3395495db301')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'30e80c13-3bd7-4c87-84cb-33e566add35b', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'33cc266c-3dde-4869-85d3-27bec18c6b4b', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'3439f07c-5bf4-4654-a29e-460c7dde4265', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'35f713c4-86cb-4e1b-9f33-34db06e3a996', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'3ae3b66f-b652-4f64-8614-64c4245d9772', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'3cceb621-02fb-43fa-a51c-669efaf7489c', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'3da7988f-10b7-4269-a2c6-838ca999c2df', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'f5f18b5c-dfa6-4e0d-a480-258cd1a9d7f5')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'3ded59a5-3eee-445c-b987-30f8a167eb7c', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'3efc9306-f5cb-435f-a53b-4299edaec91c', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'43eee485-e5f7-4b41-8e12-6c230f979327', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'44d9db80-9a4e-4bc8-83cc-10bc77de5bde', N'bf6f9891-2faf-4d63-954c-827b07511ce9', N'c22e7728-5b40-4df9-8684-dfbea720d98f')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'47bb7387-ae09-4065-86f6-c41c2c9e6632', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'48554a2f-4023-4b75-969b-880c62b2cee0', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'4caacfd3-245e-4215-bfc3-77cd1375c2ea', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'50cf6c68-34a8-4247-b437-b2fbc6d7983d', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'52f1d51c-8b3a-4a75-babe-044d99888203', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'543c42fc-85fd-4b3b-b798-ee34c8cf67da', N'cfcd8503-dda9-41d8-8368-759b018557d7', N'27e367ab-b03f-437e-a108-560fbf85e183')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'57adeb48-b17c-43bc-80c5-baf365587e17', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'369a8ae8-ae1c-4b51-92a3-3395495db301')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'5d3acbf7-fb4d-4b6f-946c-314a5175124e', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'62ed18f8-3adb-4059-90c6-ddcaf95353e7', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'67972383-4576-484d-8f87-830e70a3381d', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'6fe4a733-94df-4231-89ba-92ee216e48e9', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'7917972c-5bd4-4802-bc4c-c496106d6038', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'79b77e14-b1ab-4dfe-a855-bf08ce3a8742', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'7cede936-11cc-4198-9aee-ab57d418aedb', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'369a8ae8-ae1c-4b51-92a3-3395495db301')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'7efe2de3-7c74-4e08-b954-fe0a9c3e1901', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'84bc4113-049e-483e-be86-589ecfaafe2f', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'858346d9-9cf1-4b6b-8c93-2a966f1bc62d', N'cfcd8503-dda9-41d8-8368-759b018557d7', N'27e367ab-b03f-437e-a108-560fbf85e183')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'85c4f411-8afa-486f-8a31-9dd11286669b', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'869ad02e-cb13-4edf-bc76-170c00cac678', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'86e43beb-d43a-4083-86ee-cd31305c2268', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'886aeea0-73fb-4fca-bff5-c127419b375b', N'cfcd8503-dda9-41d8-8368-759b018557d7', N'27e367ab-b03f-437e-a108-560fbf85e183')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'88e9e52f-fd0a-4ea4-8e26-96716580bbde', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'f5f18b5c-dfa6-4e0d-a480-258cd1a9d7f5')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'8bdb64e3-e921-4da4-9d18-d4fc287995fc', N'cfcd8503-dda9-41d8-8368-759b018557d7', N'27e367ab-b03f-437e-a108-560fbf85e183')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'8cbda976-b418-400a-a7a5-105540bfb7b0', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'4e1e9c0a-332a-46a1-b739-ce6a731a514d')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'9ab606b5-7799-43e0-ba03-d2d903f7c848', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'9b553a96-2b46-4f34-9e9f-6213b9ff4a42', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'9b58a502-15c7-4319-93c6-f365a7575485', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'f5f18b5c-dfa6-4e0d-a480-258cd1a9d7f5')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'9ebea950-09bd-4d3d-83aa-605659f7ee9e', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'9ece925f-a147-4e10-ba50-e8e772eea4b3', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'a1928dfb-fe60-4f20-82bd-75f25eb6e9ed', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'a2a4496a-52e6-4ef2-8ef4-772b09ecfce9', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'a307ecf3-5a98-44d7-87f5-d8d207184c7d', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'a4d2c319-3c29-4197-8f34-9a30041ca139', N'f282783f-1d57-4321-91d1-938fcc2906f4', N'1b9febcc-0a1f-40aa-addc-1d83976acb63')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'ae09e104-8f6c-4bb4-a639-0b8c45bf5f0e', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'b0214802-00f8-4207-90d9-89ce11730a44', N'bf6f9891-2faf-4d63-954c-827b07511ce9', N'c22e7728-5b40-4df9-8684-dfbea720d98f')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'b0a43c73-8e4b-4a6e-b0fc-42a917cc075c', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'e158df8e-63fb-49a1-9ec4-ce4ccf309ee2')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'b350ef52-d9ff-47cd-b8b5-82501b384627', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'e158df8e-63fb-49a1-9ec4-ce4ccf309ee2')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'b6a50255-02ef-48b9-a22d-9a6bb2fd9453', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'bad5224c-6bed-4a1d-aa5d-e645ffa20c4f', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'bbe412b7-5211-49d3-91f6-f7ed8373bc0f', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'be8eb316-436a-4c4e-afaa-e4fa3d457ccd', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'c0990646-5b41-496e-a8bb-5fabd56f9c7c', N'f282783f-1d57-4321-91d1-938fcc2906f4', N'1b9febcc-0a1f-40aa-addc-1d83976acb63')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'c24129e1-613a-4192-b81a-59123b830072', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'4e1e9c0a-332a-46a1-b739-ce6a731a514d')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'cc33c3f7-7460-45ff-9e03-0b2a2b6ef33c', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'd53fb44c-f689-46ce-b9f5-2bc6dc05ac4e', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'd6fa861d-bdc2-4395-b3b6-67f516a280a5', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'4e1e9c0a-332a-46a1-b739-ce6a731a514d')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'd74d9fe3-9072-486e-a88a-2cb4499112d0', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'd81cadc7-9d2d-4ec2-a510-e33405e153d0', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'db013d23-3e12-4ac7-9806-d2b01edfce5b', N'3a36f7ff-b5c2-46ad-9e41-8273768dae09', N'f5f18b5c-dfa6-4e0d-a480-258cd1a9d7f5')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'e248397f-d9b7-43d9-b8fb-eff518a80547', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'e3cc57f1-d333-4ba1-83af-c4e265812dec', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'e3eae615-e37a-41cd-be3c-a7ee88078d12', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'e5b192fc-3167-49c0-8dec-18fb19d19ac0', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'e158df8e-63fb-49a1-9ec4-ce4ccf309ee2')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'e6b13666-df03-4830-ad8f-57d72a9cb6e8', N'72f79650-45bd-4705-90a2-7fc5839e3e28', N'e5f5eb4e-271f-478a-950a-f31e86c78410')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'e8a71996-6c46-47d9-94ba-4793a420e31f', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'e9e2c5d7-ad56-4e03-b265-0df2d739b122', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'e9ed05b1-e16c-40a4-9a51-e46ea6ca2c24', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'ea6f69de-3660-49ef-9440-ae31dc5d2dcd', N'99801e69-dd9e-42f0-ab32-318936bb87c4', N'101a5fb6-003f-4536-b8cc-40628b2121af')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'ec024bd6-f16e-413b-8ce3-f61d01fcfde4', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'4e1e9c0a-332a-46a1-b739-ce6a731a514d')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'edb0a198-79f1-49d6-a27e-e52b8dd4356f', N'81a8a371-40a8-42f1-808f-a686a4d8bfa3', N'4e1e9c0a-332a-46a1-b739-ce6a731a514d')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'edcdd27a-47f0-43a2-a521-1486081ce466', N'df28f4c5-8907-4bcf-a9b7-3849cb860815', N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'f025d870-2bc9-49e6-a44f-dde850e63997', N'ee40b247-2a92-4466-ba72-4a95ed32eabd', N'bf30dc89-30a1-476e-baad-b49006a154e0')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'f3c98d0a-c264-4ae3-9c2f-5ce6b22e5ffc', N'f98320c3-235a-4cb7-a0a8-eda132b0e545', N'369a8ae8-ae1c-4b51-92a3-3395495db301')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'fd232d14-843a-458a-81ff-8f2213c6667a', N'2f5923a5-64d0-4bdc-bf0b-fabfa76f0404', N'12fa2b76-3b41-461a-9700-6780d7ac196a')
INSERT [dbo].[Room] ([room_id], [hotel_id], [room_type_id]) VALUES (N'ff32c4f6-4c86-4d41-b688-3277e9290d98', N'78c3c9e6-9b8c-49fb-bfbe-0b9d616b40bd', N'e158df8e-63fb-49a1-9ec4-ce4ccf309ee2')
GO
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'038967ec-600a-4388-81d6-0ba076d23bb4', 90, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'04e6d3e9-084f-4cc4-ac56-cf00370cd2e2', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'0a45db89-a24e-4754-8a29-86f0c46fe03d', 100, N'Normal bedroom', 3, N'Normal bathroom', 4, 1, N'Guest ID', 6, 4)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'0eae0acd-c4ff-408d-899e-157e5cdc0203', 90, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'0ed93fba-7e9a-4340-a8f2-5c179038707f', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 5)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'101a5fb6-003f-4536-b8cc-40628b2121af', 100, N'Normal bedroom', 1, N'Normal bathroom', 1, 0, N'Guest ID', 5, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'12fa2b76-3b41-461a-9700-6780d7ac196a', 123, N'Normal bedroom', 8, N'Normal bathroom', 5, 0, N'Guest ID', 4, 4)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'17af024f-307d-4753-9a02-444eefc02966', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'1883170a-8e08-486d-ad35-a9da87ae7862', 100, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'1b9febcc-0a1f-40aa-addc-1d83976acb63', 150, N'Normal bedroom', 4, N'Normal bathroom', 3, 1, N'Guest ID', 5, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'27e367ab-b03f-437e-a108-560fbf85e183', 210, N'Normal bedroom', 4, N'Normal bathroom', 5, 1, N'Guest ID', 5, 4)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'369a8ae8-ae1c-4b51-92a3-3395495db301', 104, N'Normal bedroom', 7, N'Normal bathroom', 5, 1, N'Guest ID', 6, 4)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'3dc0ca45-f5bb-40b1-8e7a-6c7543021900', 111, N'Normal bedroom', 6, N'Normal bathroom', 3, 1, N'Guest ID', 4, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'3eeac8cc-d03b-44b5-a723-af0623ff949c', 100, N'Normal bedroom', 3, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'413b09e8-562d-4cdf-8fe2-412912cb5641', 150, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 10, 5)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'4cd441c1-eca3-43b6-b2b9-fe61d743d529', 90, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'4e1e9c0a-332a-46a1-b739-ce6a731a514d', 100, N'Normal bedroom', 6, N'Normal bathroom', 4, 1, N'Guest ID', 5, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'56779998-b6c3-4bd6-bddb-ac90d2bffa43', 100, N'Normal bedroom', 6, N'Normal bathroom', 4, 1, N'Guest ID', 4, 4)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'590c935b-01ef-4417-9f9a-5a64a817bf8c', 111, N'Normal bedroom', 6, N'Normal bathroom', 3, 1, N'Guest ID', 4, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'5b7b301a-da46-42b5-a87a-59e10848f033', 169, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'5dce17cd-631f-407d-949b-d067d1db09cc', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 0)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'5e332bf6-1f79-451f-90bb-465fedeccd01', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'5f3a37d4-30d8-4846-9b2b-aeb4483ee7b0', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'62887a59-491c-4736-86d3-ce0e604e4133', 100, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'693b45a8-3145-4115-95fb-018c37f1c04e', 110, N'Normal bedroom', 2, N'Normal bathroom', 3, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'6affcf39-5c5c-46dc-acb6-08ce9581ba5b', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'6c98a9a3-6c6e-4fd1-858d-79e950ce31f6', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'7143dd4f-e627-4605-bf30-3838ba75a61b', 90, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'798535c2-ee8c-4aa7-adf9-87b92386c25f', 110, N'Normal bedroom', 5, N'Normal bathroom', 5, 1, N'Guest ID', 6, 5)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'7eea8952-965f-4f60-9526-fe3950927b24', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'83ca4337-1f3e-4946-aa07-a87488a894fc', 111, N'Normal bedroom', 6, N'Normal bathroom', 3, 1, N'Guest ID', 4, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'89e42a55-e313-4beb-9032-8ecae0c7b277', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'8d71d13c-9699-4357-9dd1-54f58f08ed1b', 90, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'909cbda5-4a1b-4562-a26c-09605281de3a', 112, N'Normal bedroom', 2, N'Normal bathroom', 1, 1, N'Guest ID', 4, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'98b73f1d-1ebc-45d0-8040-d554e1ae92ce', 111, N'Normal bedroom', 6, N'Normal bathroom', 3, 1, N'Guest ID', 4, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'a5292fa8-b2dd-4dce-a985-ebcce8d5060e', 200, N'Normal bedroom', 10, N'Normal bathroom', 7, 1, N'Guest ID', 8, 6)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'ae8ed5b3-ce86-4f4f-96ac-42fb86098e1f', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'b510e8c5-3959-4d5e-a48b-d0b175f2b2d7', 98, N'Normal bedroom', 3, N'Normal bathroom', 1, 1, N'Guest ID', 1, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'bf30dc89-30a1-476e-baad-b49006a154e0', 120, N'Normal bedroom', 4, N'Normal bathroom', 4, 1, N'Guest ID', 5, 4)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'c22e7728-5b40-4df9-8684-dfbea720d98f', 100, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'c2c658a3-d7b3-47b3-969e-3605440ab17e', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'c41ea8b1-3386-4e82-8280-08bd07bf17d4', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'c524413c-d9f2-4dbc-8c1a-c0796bbf49a3', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'c9ba26f5-3c6b-45dd-9879-67f3eac56e17', 100, N'Normal bedroom', 4, N'Normal bathroom', 1, 1, N'Guest ID', 2, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'da5ddda7-ca76-4fa3-98f2-e5d48674e2af', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'dd33d098-5f48-4735-a178-7623d83c6ac8', 100, N'Normal bedroom', 3, N'Normal bathroom', 2, 1, N'Guest ID', 3, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'dda2c8cb-e7c6-4304-b255-89bf7b59881b', 100, N'Normal bedroom', 2, N'Normal bathroom', 2, 1, N'Guest ID', 11, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'df35f91e-b9d2-40f2-bdd8-5324a7aae195', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'e158df8e-63fb-49a1-9ec4-ce4ccf309ee2', 105, N'Normal bedroom', 3, N'Normal bathroom', 2, 1, N'Guest ID', 3, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'e1bb1be3-a158-4ecd-9187-7e194a0a8629', 169, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'e5f5eb4e-271f-478a-950a-f31e86c78410', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 1, N'Guest ID', 6, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'e667f77e-58ec-43b9-8ca8-7c9f1a006ec9', 90, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'e69685ae-a689-44c3-9e5b-b6eb570f8748', 100, N'Normal bedroom', 6, N'Normal bathroom', 1, 0, N'Guest ID', 1, 2)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'eb92574e-efd0-46ca-a887-7d3781c0279a', 111, N'Normal bedroom', 6, N'Normal bathroom', 3, 1, N'Guest ID', 4, 3)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'ed77c49e-2b67-49b2-b48f-2490a30e6337', 100, N'Normal bedroom', 2, N'Normal bathroom', 1, 0, N'Guest ID', 2, 1)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'f5f18b5c-dfa6-4e0d-a480-258cd1a9d7f5', 115, N'Normal bedroom', 8, N'Normal bathroom', 6, 1, N'Guest ID', 4, 4)
INSERT [dbo].[RoomType] ([id], [price], [bed_type], [bed_number], [bathroom_type], [bathroom_number], [is_private_bathroom], [guests_id], [number_of_guests], [number_of_room]) VALUES (N'ffc9c34b-372c-4b1e-a536-61d6c26cd748', 90, N'Normal bedroom', 6, N'Normal bathroom', 1, 1, N'Guest ID', 3, 2)
GO
SET IDENTITY_INSERT [dbo].[Transact] ON 

INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (1, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-07T07:41:31.060' AS DateTime), 150, 0, N'1cc40572-5e69-11ed-9b6a-0242ac120002')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (2, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-07T07:41:31.060' AS DateTime), 130, 0, N'2096880a-5e69-11ed-9b6a-0242ac120002')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (3, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-07T18:59:24.077' AS DateTime), 420, 0, N'706236c4-520a-4178-8fe0-5b9c52271be9')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (4, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-07T19:23:24.430' AS DateTime), 246, 0, N'10c09e93-278e-489b-93a2-153c5c3a6ac1')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (5, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-07T19:26:36.623' AS DateTime), 246, 1, N'10c09e93-278e-489b-93a2-153c5c3a6ac1')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (6, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-07T19:38:01.940' AS DateTime), 246, 0, N'c45f55ed-219f-4623-adf5-07b3e54dc034')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (7, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-07T19:38:20.517' AS DateTime), 246, 1, N'c45f55ed-219f-4623-adf5-07b3e54dc034')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (8, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T00:11:37.450' AS DateTime), 210, 0, N'c2dc968d-a272-421f-808c-6c12bf32e4ee')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (9, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T04:30:34.847' AS DateTime), 400, 0, N'2166304a-49e1-4608-87f1-24fb755e9860')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (10, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T09:29:35.903' AS DateTime), 500, 0, N'02589164-8b25-4ba7-9253-7dafac512888')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (11, N'e1078832-9c59-44a0-8d5e-39cd60c44822', CAST(N'2022-11-08T09:30:15.980' AS DateTime), 500, 1, N'02589164-8b25-4ba7-9253-7dafac512888')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (12, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T10:34:10.480' AS DateTime), 200, 0, N'5303759a-2cc6-45d7-8c38-7f2fa5831b07')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (13, N'e1078832-9c59-44a0-8d5e-39cd60c44822', CAST(N'2022-11-08T10:37:35.133' AS DateTime), 200, 1, N'5303759a-2cc6-45d7-8c38-7f2fa5831b07')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (14, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T10:51:58.030' AS DateTime), 200, 0, N'86d8d68f-c5e1-4884-add8-bdcd53f806fe')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (15, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T10:59:19.033' AS DateTime), 369, 0, N'953b7e1d-0bff-4354-8d49-42c4ae3f44b7')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (16, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T10:59:34.763' AS DateTime), 246, 0, N'47a2c812-c673-4d19-83a5-edaf2677867f')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (17, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-08T11:00:07.067' AS DateTime), 246, 1, N'47a2c812-c673-4d19-83a5-edaf2677867f')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (18, N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', CAST(N'2022-11-08T11:00:15.300' AS DateTime), 369, 1, N'953b7e1d-0bff-4354-8d49-42c4ae3f44b7')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (19, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T11:06:50.850' AS DateTime), 300, 0, N'dc3f6e1a-b42a-49af-ac15-a02953b565a2')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (20, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T11:07:04.000' AS DateTime), 300, 0, N'3da29cc5-bf38-4252-9975-5c3b0f21f843')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (21, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T11:07:14.843' AS DateTime), 300, 0, N'ee4c0d72-b30f-49da-b2fd-ba08a03c7ea4')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (22, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T11:07:52.103' AS DateTime), 300, 1, N'3da29cc5-bf38-4252-9975-5c3b0f21f843')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (23, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T11:08:03.140' AS DateTime), 300, 1, N'dc3f6e1a-b42a-49af-ac15-a02953b565a2')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (24, N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', CAST(N'2022-11-08T11:08:05.583' AS DateTime), 300, 1, N'ee4c0d72-b30f-49da-b2fd-ba08a03c7ea4')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (25, N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08T11:15:39.020' AS DateTime), 300, 0, N'da9cf487-e3b5-4dbd-93f3-c93379fe68ae')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (26, N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08T11:15:50.133' AS DateTime), 300, 0, N'5e4380a6-ab64-4859-9076-1fe52a1eb370')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (27, N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08T11:15:55.590' AS DateTime), 300, 0, N'a14a8cf2-4f4e-40bf-918a-f3b25ac17c82')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (28, N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08T11:17:23.200' AS DateTime), 300, 2, N'a14a8cf2-4f4e-40bf-918a-f3b25ac17c82')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (29, N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08T11:17:25.073' AS DateTime), 300, 2, N'5e4380a6-ab64-4859-9076-1fe52a1eb370')
INSERT [dbo].[Transact] ([id], [user_id], [createdAt], [amount], [type], [booking_id]) VALUES (30, N'7f7076c1-c7a1-4111-a01f-597160769e30', CAST(N'2022-11-08T11:17:26.570' AS DateTime), 300, 2, N'da9cf487-e3b5-4dbd-93f3-c93379fe68ae')
SET IDENTITY_INSERT [dbo].[Transact] OFF
GO
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'123', N'sa', N'123', N'123', N'123', N'123', N'123', 123, N'123', N'123', N'123', N'123', NULL, NULL, CAST(N'2022-11-01' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'1234', N'12345', N'12345', N'12345', NULL, NULL, NULL, 1, NULL, NULL, NULL, N'1234', NULL, NULL, CAST(N'2022-11-05' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'71057869-312d-4695-a586-76bf1ccb762e', N'de1605526', N'/FJ1uhdUeZHCbDSHO/eMSL7Fxm97vmhld4rfoIgEbQ4=', N'duclqde160552@fpt.edu.vn', NULL, NULL, NULL, 2, NULL, NULL, NULL, N'0xKQiWQcCVjvStFoBvQr6ZQd1KagEN', NULL, NULL, CAST(N'2022-11-05' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'7f7076c1-c7a1-4111-a01f-597160769e30', N'duc1234', N'uDmyNfQpamlgGksLXt2SW0tfqYvhjAxMua7LFsKwbAA=', N'totenduc1307@gmail.com', NULL, NULL, NULL, 2, NULL, NULL, NULL, N'RK0aaQtN9wHDsYXOhqfgvYSM2BaeCX', NULL, NULL, CAST(N'2022-11-08' AS Date), 4)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'8c91117d-465c-4433-96dc-d066b21a478a', N'de1605520', N'8ZmyhyEdIJUQFg7DBei0pZgJvYWxYQOQ7yqmnEKyq6k=', N'totenduc1307@gmail.com', NULL, NULL, NULL, 1, NULL, NULL, NULL, N'qsoztt4muPhNDE1sl6T7bo5N4FXnQ0', NULL, NULL, CAST(N'2022-11-06' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'8f850c39-086f-4c15-aede-ab7e00d26dd4', N'de160552', N'E+xQ9wXFLls7hK63qvvck5H0D4O2SmDzl6xMz5qHN8M=', N'totenduc1307@gmail.com', NULL, NULL, NULL, 2, NULL, NULL, NULL, N'HBMB2kz3nsLJazDmSGE2U8wsFJXzF2', NULL, NULL, CAST(N'2022-11-05' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'9ad6286d-d08d-4397-8704-b8a1aff07309', N'duc', N'hck/iR1by2E1Tx3fjs/Jux05xR471uvfRDOiNV2tI/s=', N'duc@gmail.com', N'12312512556', N'Duc4', N'http://localhost:8080/bookify/images/users/buffet.png', 1, NULL, NULL, N'asdasdasdasdasdádasdasđ12d12d', N'zL4M79NfLg0Yvi245WS9HRrdwvG7aT', N'Le Quy DD', CAST(N'2002-07-24' AS Date), CAST(N'2022-11-01' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'ab1c511d-cd8f-419e-9276-94f2a57e2da3', N'nguoidung', N'awyOu96SMY9HjBRrzidTn8mMtanHok3Om2m+Abr473A=', N'totenduc13072002@gmail.com', N'undefined', N'Dung', N'http://localhost:8080/bookify/images/users/blankAvatar.jpg', 2, NULL, NULL, N'undefined', N'rV3L3pb7YPe4Nr04x11dkP5LtE9je6', N'Nguoi', CAST(N'2022-11-12' AS Date), CAST(N'2022-11-07' AS Date), 2)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'b955c796-027c-4e11-92ff-1bb942a102c8', N'leduc123', N'OZPJVZpFYQaxVkd8frspwDibUzmbAW8iuZbKnnPeM0o=', N'duc@gmail.com', N'890896756756', N'Duc', N'http://localhost:8080/bookify/images/users/LofiGirlonTwitter.png', 2, NULL, NULL, N'Day la le duc tesst', N'gKkg5S8AFzQ2SOm2s6m4EcllaQQgMJ', N'Le', CAST(N'2002-07-24' AS Date), CAST(N'2022-11-01' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'd2740e89-004f-463c-8c76-4df297c3c486', N'leduc1236', N'UJa0BQutMZIEja2pXtqDONDP0GH3jF/o5giHUK6IpmU=', N'totenduc13072002@gmail.com', NULL, NULL, NULL, 1, NULL, NULL, NULL, N'FQ6I6FataGFvcsiqLNQfRDomZUHUOJ', NULL, NULL, CAST(N'2022-11-06' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3', N'de1605528', N'WPrDgVYFTTD4sBYzMY/GDvk/vNLAQJ3ps6JKvOiqYuM=', N'totenduc1307@gmail.com', N'1231245125612', N'Duc', N'http://localhost:8080/bookify/images/users/blossom-hotel-houston.jpg', 2, NULL, NULL, N'iawhd aiwdha iwudh awiduha iwudh awidhaiwud hawiudh auwhauwd aidwh ad jaid aidh aiwdh aiwudh ưd', N'UzYYmaDSZS6iEC2eavSMjHh4rnlWtd', N'Le Quy', CAST(N'2002-06-17' AS Date), CAST(N'2022-11-06' AS Date), 1)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'e1078832-9c59-44a0-8d5e-39cd60c44822', N'quocdat', N'g7qIJXzMK9mDAIjG0+9AOX891Zko3rXO6VBcum0t9vk=', N'quocdat@gmail.com', NULL, NULL, NULL, 2, NULL, NULL, NULL, N'B22QAvMBm050iCItlZfjLKyXBpBjV2', NULL, NULL, CAST(N'2022-11-08' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'e8762b7e-b5fb-44d6-8d50-579d2ea3a0cd', N'newAcc12', N'UQJaLCBf3cJo+notMKopfoR9mIzwHf6xc7xsbRCJdi4=', N'totenduc13072002@gmail.com', N'1233453', N'Acc', N'http://localhost:8080/bookify/images/users/null', 1, NULL, NULL, N'ahw daiuwdh aiwudh iawdh aiwudh aiuwdh aiuwdhaiudh aiudh aiuwdhai diuadwh aiuwhd iawdh aiuwdh iawdh ', N'WAALS4bIZJrgJU5QC00daElEKKFrbR', N'New', CAST(N'2002-02-06' AS Date), CAST(N'2022-11-05' AS Date), NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [subname], [dob], [signAt], [banking_account_id]) VALUES (N'f36c3bae-df3a-449a-b5fc-9d9ec53833bb', N'de1605521', N'eoowybX3Fh61sGUTu7lIPT/djLgIREeeROgDAH6l/xk=', N'duc2@gmail.com', NULL, NULL, NULL, 3, NULL, NULL, NULL, N'TjBRx1jem5LhLGKF6Es5xsC3A6FHzn', NULL, NULL, CAST(N'2022-11-05' AS Date), NULL)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__userDeta__F3DBC5728063A8EA]    Script Date: 11/9/2022 10:30:05 PM ******/
ALTER TABLE [dbo].[userDetail] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Jan]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Feb]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Mar]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Apr]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [May]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Jun]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Jul]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Aug]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Sep]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Oct]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Nov]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT ((0)) FOR [Dec]
GO
ALTER TABLE [dbo].[hotelView] ADD  DEFAULT (datepart(year,getdate())) FOR [YearTime]
GO
ALTER TABLE [dbo].[Amenity]  WITH CHECK ADD  CONSTRAINT [FK_Amenity_AmenityType] FOREIGN KEY([type_id])
REFERENCES [dbo].[Amenity_type] ([amenity_type_id])
GO
ALTER TABLE [dbo].[Amenity] CHECK CONSTRAINT [FK_Amenity_AmenityType]
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_room_id_ref] FOREIGN KEY([room_id])
REFERENCES [dbo].[Room] ([room_id])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_room_id_ref]
GO
ALTER TABLE [dbo].[BookingRoom]  WITH CHECK ADD FOREIGN KEY([booking_id])
REFERENCES [dbo].[Booking] ([booking_id])
GO
ALTER TABLE [dbo].[BookingRoom]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
GO
ALTER TABLE [dbo].[Bookmark]  WITH CHECK ADD  CONSTRAINT [FK__Bookmark__hotel___4D94879B] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Bookmark] CHECK CONSTRAINT [FK__Bookmark__hotel___4D94879B]
GO
ALTER TABLE [dbo].[Bookmark]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
GO
ALTER TABLE [dbo].[Hotel]  WITH CHECK ADD  CONSTRAINT [FK__Hotel__hoteltype__48CFD27E] FOREIGN KEY([hoteltype_id])
REFERENCES [dbo].[hotelType] ([hoteltype_id])
GO
ALTER TABLE [dbo].[Hotel] CHECK CONSTRAINT [FK__Hotel__hoteltype__48CFD27E]
GO
ALTER TABLE [dbo].[Hotel]  WITH CHECK ADD  CONSTRAINT [FK__Hotel__user_id__49C3F6B7] FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
GO
ALTER TABLE [dbo].[Hotel] CHECK CONSTRAINT [FK__Hotel__user_id__49C3F6B7]
GO
ALTER TABLE [dbo].[HotelAmenities]  WITH CHECK ADD FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[HotelAmenities]  WITH CHECK ADD  CONSTRAINT [FK_HotelAmenities_Amenity] FOREIGN KEY([amenity_id])
REFERENCES [dbo].[Amenity] ([amenity_id])
GO
ALTER TABLE [dbo].[HotelAmenities] CHECK CONSTRAINT [FK_HotelAmenities_Amenity]
GO
ALTER TABLE [dbo].[hotelView]  WITH CHECK ADD FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Image]  WITH CHECK ADD  CONSTRAINT [FK__Image__hotel_id__6383C8BA] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Image] CHECK CONSTRAINT [FK__Image__hotel_id__6383C8BA]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD  CONSTRAINT [FK__Report__hotel_id__5BE2A6F2] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Report] CHECK CONSTRAINT [FK__Report__hotel_id__5BE2A6F2]
GO
ALTER TABLE [dbo].[Report]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD  CONSTRAINT [FK__Review__hotel_id__5FB337D6] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Review] CHECK CONSTRAINT [FK__Review__hotel_id__5FB337D6]
GO
ALTER TABLE [dbo].[Review]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
GO
ALTER TABLE [dbo].[Room]  WITH CHECK ADD  CONSTRAINT [fk_hotel] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Room] CHECK CONSTRAINT [fk_hotel]
GO
ALTER TABLE [dbo].[Room]  WITH CHECK ADD  CONSTRAINT [fk_room_type] FOREIGN KEY([room_type_id])
REFERENCES [dbo].[RoomType] ([id])
GO
ALTER TABLE [dbo].[Room] CHECK CONSTRAINT [fk_room_type]
GO
ALTER TABLE [dbo].[RuleDetail]  WITH CHECK ADD  CONSTRAINT [FK__RuleDetai__hotel__5629CD9C] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[RuleDetail] CHECK CONSTRAINT [FK__RuleDetai__hotel__5629CD9C]
GO
ALTER TABLE [dbo].[Transact]  WITH CHECK ADD FOREIGN KEY([booking_id])
REFERENCES [dbo].[Booking] ([booking_id])
GO
ALTER TABLE [dbo].[Transact]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
GO
ALTER TABLE [dbo].[userDetail]  WITH CHECK ADD FOREIGN KEY([banking_account_id])
REFERENCES [dbo].[BankingAccount] ([id])
GO
/****** Object:  StoredProcedure [dbo].[proc_bookingRoom]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_bookingRoom]
@bookingId varchar(50),
@userId varchar(50),
@checkin date,
@checkout date,
@adult int,
@child int,
@infants int,
@pets int,
@roomId varchar(50)
as
begin
	-- Booking status
	-- 0: pending
	-- 1: accepted
	-- 2: rejected

	insert into Booking values
		(
			@bookingId, @userId, @checkin, @checkout, 
			@adult, @child, @infants, @pets, 
			@roomId, 0, getDate() 
		)
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllBookmarkedHotel]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getAllBookmarkedHotel]
@user_id varchar(50)
as
begin
	select distinct *
	from 
		Hotel join Bookmark 
			on Hotel.hotel_id = Bookmark.hotel_id
	where Bookmark.user_id = @user_id
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllCheckout]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getAllCheckout]
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, Booking.check_out, getdate()) >= 0
	and Room.hotel_id = @hotelId
	and Booking.status = 1
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllHotelBasicInfor]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create   procedure [dbo].[proc_getAllHotelBasicInfor]
@userId varchar(50)
as
begin
	select bab.*, 
( select count(*) from Bookmark as bm where bm.hotel_id=bab.hotel_id and bm.user_id=@userId) as isBookmarked
from getAllHotelBasicInfo as bab
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllHotelBasicInforByAmenityId]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_getAllHotelBasicInforByAmenityId]
@userId varchar(50), @amenityId varchar(50)  as
begin
	select bab.*, 
	( select count(*) from Bookmark as bm where bm.hotel_id=bab.hotel_id and bm.user_id=@userId) as isBookmarked
	from getAllHotelBasicInfo as bab where bab.hotel_id in (select hotel_id from HotelAmenities where amenity_id=@amenityId)
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllHotelBasicInforByHotelType]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_getAllHotelBasicInforByHotelType]
@userId varchar(50), @hoteltypeId varchar(50)  as
begin
	select bab.*, 
	( select count(*) from Bookmark as bm where bm.hotel_id=bab.hotel_id and bm.user_id=@userId) as isBookmarked
	from getAllHotelBasicInfo as bab where bab.hoteltype_id=@hoteltypeId
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllIncomingBooking]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getAllIncomingBooking]
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, getdate(), Booking.check_in) > 0
	and Room.hotel_id = @hotelId
	and Booking.status = 1
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllMergedDayBooking]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   procedure [dbo].[proc_getAllMergedDayBooking]
@check_in varchar(50), @check_out varchar(50), @hotelId varchar(50)  as
begin
	select r.room_id, dr.check_in, dr.check_out from Room as r, dateRangeBookingMerged as dr
	where dr.room_id=r.room_id and
	r.room_id not in
	(select room_id from dateRangeBookingMerged where 
	((check_in>=@check_in and check_out<=@check_out) 
	or (check_in>@check_in and check_out<@check_out) 
	or (check_in<@check_in and check_out>@check_out))
	and hotel_id=@hotelId) and r.hotel_id=@hotelId
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllNotification]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getAllNotification]
@userId varchar(50)
as
begin
	select joinedNotifs.notify_id, joinedNotifs.user_id, joinedNotifs.source_id,
			joinedNotifs.notify_date, joinedNotifs.notify_type, joinedNotifs.is_read,
			joinedNotifs.actor_id, joinedNotifs.hotel_id, Hotel.hotel_name, 
			userDetail.username as actorName, userDetail.avatar as actorAvatar from
	(
		select 
			Notification.notify_id, Notification.user_id, Notification.source_id,
			Notification.notify_date, Notification.notify_type, Notification.is_read,
			Booking.user_id as actor_id, Room.hotel_id
		from 
			Notification join Booking on Notification.source_id = Booking.booking_id
			join Room on Booking.room_id = Room.room_id
			where Notification.notify_type in (0, 3, 4, 5) 
		union
		select 
			Notification.notify_id, Notification.user_id, Notification.source_id,
			Notification.notify_date, Notification.notify_type, Notification.is_read,
			Review.user_id as actor_id, Review.hotel_id
		from 
			Notification join Review on Notification.source_id = Review.review_id
			where Notification.notify_type = 1
	) as joinedNotifs join Hotel on joinedNotifs.hotel_id = Hotel.hotel_id
	join userDetail on joinedNotifs.actor_id = userDetail.user_id
	where joinedNotifs.user_id = @userId
	order by notify_date desc
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllPendingBooking]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getAllPendingBooking]
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		Room.hotel_id = @hotelId
	and Booking.status = 0
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllTodayBookedBooking]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getAllTodayBookedBooking]
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, getdate(), Booking.check_out) > 0
	and Room.hotel_id = @hotelId
	and Booking.status = 1
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllTodayCheckout]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getAllTodayCheckout]
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, getdate(), Booking.check_out) = 0
	and Room.hotel_id = @hotelId
	and Booking.status = 1
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAllTodayPendingBooking]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getAllTodayPendingBooking]
@hotelId varchar(50)
as
begin
	select * 
	from 
		Booking join userDetail on Booking.user_id = userDetail.user_id
		join Room on Booking.room_id = Room.room_id
		join RoomType on Room.room_type_id = RoomType.id
	where 
		datediff(day, Booking.bookAt, getDate()) = 0
	and Room.hotel_id = @hotelId
	and Booking.status = 0
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getAmount]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_getAmount]
@userId varchar(50)
as
begin
	select amount 
	from 
		userDetail join BankingAccount
		on userDetail.banking_account_id = BankingAccount.id
	where userDetail.user_id = @userId
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getDefaultAmenities]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_getDefaultAmenities]
as
begin
	select * 
	from Amenity join Amenity_type
	on Amenity.type_id = Amenity_type.amenity_type_id
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getHotelAmenities]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_getHotelAmenities]
@hotelId varchar(50)
as
begin
	select * 
	from 
		HotelAmenities join Amenity 
		on HotelAmenities.amenity_id = Amenity.amenity_id
		join Amenity_type 
		on Amenity.type_id = Amenity_type.amenity_type_id
	where HotelAmenities.hotel_id = @hotelId
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getHotelIdFromOwnerId]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   proc [dbo].[proc_getHotelIdFromOwnerId]
@ownerId varchar(50)
as
begin
	select Hotel.hotel_id 
	from Hotel
	where Hotel.user_id = @ownerId
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getRoomType]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_getRoomType]
@hotelId varchar(50)
as
begin
	select 
		RoomType.id, RoomType.price, RoomType.bed_type, 
		RoomType.bed_number, RoomType.bathroom_type, 
		RoomType.bathroom_number, RoomType.is_private_bathroom, 
		RoomType.is_private_bathroom, RoomType.number_of_guests,
		RoomType.number_of_room, Room.hotel_id, count(Room.room_id) as rooms 
	from RoomType join Room
	on RoomType.id = Room.room_type_id
	group by RoomType.id, RoomType.price, RoomType.bed_type, 
		RoomType.bed_number, RoomType.bathroom_type, 
		RoomType.bathroom_number, RoomType.is_private_bathroom, 
		RoomType.is_private_bathroom, RoomType.number_of_guests,
		RoomType.number_of_room, Room.hotel_id
	having Room.hotel_id = @hotelId
end
GO
/****** Object:  StoredProcedure [dbo].[proc_getTransactionDataHistory]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_getTransactionDataHistory]
@userId varchar(50), @month int
as
begin
	select ts.*, CONVERT(VARCHAR(5),createdAt,108) as specTime, ba.amount as wallet, ht.hotel_name, bk.adult, bk.child, bk.infants, bk.pets
	from Transact ts, booking bk, room rm, Hotel ht, BankingAccount ba, userDetail ud where 
	ts.booking_id=bk.booking_id and bk.room_id=rm.room_id and rm.hotel_id=ht.hotel_id and ud.banking_account_id=ba.id and bk.user_id = ud.user_id
	and ts.user_id=@userId and ts.user_id=ud.user_id and month(ts.createdAt)=@month
end
GO
/****** Object:  StoredProcedure [dbo].[proc_updateHotel]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_updateHotel]
@hotelId varchar(50),
@hotelTypeId varchar(50),
@hotelName nvarchar(50),
@backgroundImage varchar(150),
@description nvarchar(300),
@country nvarchar(100),
@district nvarchar(100),
@city nvarchar(100),
@address nvarchar(100),
@isAllowPet bit,
@isHasCamera bit,
@closing varchar(5),
@opening varchar(5),
@checkin varchar(5),
@checkout varchar(5)
as
begin
	update Hotel
	set 
		hoteltype_id = @hotelTypeId,
		hotel_name = @hotelName,
		background_image = @backgroundImage,
		description = @description,
		country = @country,
		district = @district,
		city = @city,
		address = @address,
		isAllowPet = @isAllowPet,
		isHasCamera = @isHasCamera,
		closing = @closing,
		opening = @opening,
		checkin = @checkin,
		checkout = @checkout
	where hotel_id = @hotelId
end
GO
/****** Object:  StoredProcedure [dbo].[proc_updateRoomType]    Script Date: 11/9/2022 10:30:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create   procedure [dbo].[proc_updateRoomType]
@id varchar(50),
@price int,
@numberOfBed int,
@numberOfBathroom int,
@isPrivateBathroom bit,
@numberOfGuests int,
@numberOfRoom int
as
begin
	update RoomType
	set 
		price = @price,
		bed_number = @numberOfBed,
		bathroom_number = @numberOfBathroom,
		is_private_bathroom = @isPrivateBathroom,
		number_of_guests = @numberOfGuests,
		number_of_room = @numberOfRoom
	where id = @id
end
GO
USE [master]
GO
ALTER DATABASE [bookify] SET  READ_WRITE 
GO
