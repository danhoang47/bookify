USE [master]
GO
/****** Object:  Database [bookify]    Script Date: 10/21/2022 8:54:05 AM ******/
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
/****** Object:  Table [dbo].[Amenity]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Amenity](
	[amenity_id] [varchar](50) NOT NULL,
	[amenity_name] [nvarchar](100) NULL,
	[icon] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[amenity_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Booking]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Booking](
	[booking_id] [varchar](50) NOT NULL,
	[user_id] [varchar](50) NULL,
	[guest_picker_id] [varchar](50) NULL,
	[check_in] [date] NULL,
	[check_out] [date] NULL,
	[adult] [int] NULL,
	[child] [int] NULL,
	[infants] [int] NULL,
	[pets] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[booking_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BookingRoom]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookingRoom](
	[booking_id] [varchar](50) NULL,
	[user_id] [varchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bookmark]    Script Date: 10/21/2022 8:54:05 AM ******/
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
/****** Object:  Table [dbo].[Hotel]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hotel](
	[hotel_id] [varchar](50) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[hoteltype_id] [varchar](50) NOT NULL,
	[hotel_name] [nvarchar](50) NULL,
	[rating_id] [varchar](50) NULL,
	[background_image] [varchar](150) NULL,
	[is_verified] [bit] NULL,
	[description] [nvarchar](300) NOT NULL,
	[country] [nvarchar](100) NULL,
	[district] [nvarchar](100) NULL,
	[city] [nvarchar](100) NULL,
	[street_name] [nvarchar](100) NULL,
	[street_number] [nvarchar](100) NULL,
 CONSTRAINT [PK__Hotel__45FE7E264DFD887D] PRIMARY KEY CLUSTERED 
(
	[hotel_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HotelAmenities]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HotelAmenities](
	[hotel_amenities_id] [varchar](50) NOT NULL,
	[amenity_id] [varchar](50) NULL,
	[quantity] [int] NULL,
	[hotel_id] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[hotel_amenities_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hotelType]    Script Date: 10/21/2022 8:54:05 AM ******/
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
/****** Object:  Table [dbo].[Image]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Image](
	[image_id] [varchar](50) NOT NULL,
	[hotel_id] [varchar](50) NULL,
	[image] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[image_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notification]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notification](
	[notify_id] [varchar](50) NOT NULL,
	[source_id] [varchar](50) NULL,
	[notify_type] [bit] NULL,
	[notify_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[notify_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rating]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rating](
	[rating_id] [varchar](50) NOT NULL,
	[hotel_id] [varchar](50) NULL,
	[user_id] [varchar](50) NULL,
	[communication_point] [int] NULL,
	[accurary_point] [int] NULL,
	[loccation_point] [int] NULL,
	[value_point] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[rating_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Report]    Script Date: 10/21/2022 8:54:05 AM ******/
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
	[report_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[report_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Review]    Script Date: 10/21/2022 8:54:05 AM ******/
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
	[create_at] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[review_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Room]    Script Date: 10/21/2022 8:54:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Room](
	[room_id] [varchar](50) NULL,
	[hotel_id] [varchar](50) NULL,
	[room_num] [nvarchar](50) NULL,
	[roomt_type] [smallint] NULL,
	[room_price] [float] NULL,
	[available] [bit] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RuleDetail]    Script Date: 10/21/2022 8:54:05 AM ******/
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
/****** Object:  Table [dbo].[userDetail]    Script Date: 10/21/2022 8:54:05 AM ******/
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
	[account_number] [varchar](50) NULL,
	[subname] [nvarchar](100) NULL,
	[dob] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', N'Pool', N'icon1.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'0d9fd954-78d7-49ae-a465-043be9c1d07c', N'Wifi', N'icon10.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', N'Exercise equipment', N'icon9.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'6ac90bf9-0a08-48c2-a2aa-3092c6f3574f', N'BBQ grill', N'icon4.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'73c67c79-1657-4b03-9b8b-925d4f38d48d', N'Fire pit', N'icon5.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'81995780-b20c-4b4b-9219-8187ce831359', N'Indoor fireplace', N'icon7.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'8a436006-7e92-49fa-a9ca-54c0705fcf74', N'Hot tub', N'icon2.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'99fd19a0-818b-412f-a6e2-fc13f69dff58', N'Outdoor dining area', N'icon8.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'd26df333-4df2-4e84-94ee-80eaa6bd97a9', N'Air conditioning', N'icon11.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'eba60c2c-60e7-4bf3-9376-b55e0028d4c1', N'Patio', N'icon3.png')
INSERT [dbo].[Amenity] ([amenity_id], [amenity_name], [icon]) VALUES (N'fe384dc7-b633-4ed8-a86e-60a2fb4705f0', N'Pool table', N'icon6.png')
GO
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [rating_id], [background_image], [is_verified], [description], [country], [district], [city], [street_name], [street_number]) VALUES (N'6396cf2d-b296-4ffa-8999-cab9a90b13d8', N'123', N'91bb91b4-adcb-45ba-a0bf-1669a4d5878f', N'hotel2', NULL, N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/users/StrawberryEarlGreyLatte.jpg', 0, N'This is hotel 2', N'Vietnam', N'Hue', N'Hue', N'Nguyen Hue', N'17')
INSERT [dbo].[Hotel] ([hotel_id], [user_id], [hoteltype_id], [hotel_name], [rating_id], [background_image], [is_verified], [description], [country], [district], [city], [street_name], [street_number]) VALUES (N'c614ae35-48f2-4b36-a403-ddacd3f44456', N'123', N'5403d2ea-0c94-44b8-81f7-7b4986051570', N'Hotel Sign Test 1', NULL, NULL, 0, N'This is Hotel 1', N'Vietnam', N'Hue', N'Hue', N'Nguyen Hue', N'17')
GO
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [quantity], [hotel_id]) VALUES (N'20e02a78-7a7c-4f23-a0d7-bb9265245bb7', N'56b783ce-d881-4287-b0e7-0a8f8f6140cf', 1, N'c614ae35-48f2-4b36-a403-ddacd3f44456')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [quantity], [hotel_id]) VALUES (N'29f7d2ec-0c66-4246-b232-996524f36112', N'8a436006-7e92-49fa-a9ca-54c0705fcf74', 1, N'6396cf2d-b296-4ffa-8999-cab9a90b13d8')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [quantity], [hotel_id]) VALUES (N'4dfbcc3f-81c2-4ab3-aff7-749d1e686e44', N'0d9fd954-78d7-49ae-a465-043be9c1d07c', 1, N'6396cf2d-b296-4ffa-8999-cab9a90b13d8')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [quantity], [hotel_id]) VALUES (N'd212a50a-a863-4c06-8143-4ec7524d41da', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', 1, N'c614ae35-48f2-4b36-a403-ddacd3f44456')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [quantity], [hotel_id]) VALUES (N'f109f3ac-04e0-43fd-bc09-1889d73f31f9', N'0d9fd954-78d7-49ae-a465-043be9c1d07c', 1, N'c614ae35-48f2-4b36-a403-ddacd3f44456')
INSERT [dbo].[HotelAmenities] ([hotel_amenities_id], [amenity_id], [quantity], [hotel_id]) VALUES (N'f3bfb477-4db8-446a-8590-b0268292cf00', N'01d8e6d4-bd0e-49d4-b21a-660cd2809184', 1, N'6396cf2d-b296-4ffa-8999-cab9a90b13d8')
GO
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'314ec1bd-204b-4e8e-8b3a-098b4cc110fa', N'House', N'image2.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'39ed093c-720a-4fc7-aca5-70b15d338da8', N'Boutique hotel', N'image5.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'5403d2ea-0c94-44b8-81f7-7b4986051570', N'Bed and breakfast', N'image5.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'91bb91b4-adcb-45ba-a0bf-1669a4d5878f', N'Apartment', N'image1.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'9d5050f0-3d2f-4c80-a6f5-d82a215dda21', N'Unique space', N'image4.png')
INSERT [dbo].[hotelType] ([hoteltype_id], [hoteltype], [icon]) VALUES (N'afbd51cf-8353-4303-ab47-f0785dc9c34f', N'Secondary unit', N'image3.png')
GO
INSERT [dbo].[Image] ([image_id], [hotel_id], [image]) VALUES (N'0b0c8014-bbd8-4fd5-a8af-7cfeb1cea3e5', N'c614ae35-48f2-4b36-a403-ddacd3f44456', N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/hotels/GongChaMilkFoamEarlGrey.png')
INSERT [dbo].[Image] ([image_id], [hotel_id], [image]) VALUES (N'17d4fcdd-1e95-4212-a4c2-03aee134d6cf', N'6396cf2d-b296-4ffa-8999-cab9a90b13d8', N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/hotels/OkinawaFreshMilk.jpg')
INSERT [dbo].[Image] ([image_id], [hotel_id], [image]) VALUES (N'2f2aa97f-3558-4364-ad0c-cd98df561fd4', N'c614ae35-48f2-4b36-a403-ddacd3f44456', N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/hotels/GongChaMilkFoamGreenTea.png')
INSERT [dbo].[Image] ([image_id], [hotel_id], [image]) VALUES (N'66eaabe7-d9e0-4918-8f17-609dbdd05fd3', N'6396cf2d-b296-4ffa-8999-cab9a90b13d8', N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/hotels/MangoMatchaLatte.jpg')
INSERT [dbo].[Image] ([image_id], [hotel_id], [image]) VALUES (N'6d6853ee-b9f4-4560-ba01-e6edbe7a1a3d', N'c614ae35-48f2-4b36-a403-ddacd3f44456', N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/hotels/GongChaMilkFoamAlisanTea.png')
INSERT [dbo].[Image] ([image_id], [hotel_id], [image]) VALUES (N'f0f94edf-04aa-4198-9843-fac4628390aa', N'c614ae35-48f2-4b36-a403-ddacd3f44456', N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/hotels/GongChaMilkFoamBlackTea.png')
INSERT [dbo].[Image] ([image_id], [hotel_id], [image]) VALUES (N'f4ccecc1-eb43-4240-9077-891d4c78c913', N'6396cf2d-b296-4ffa-8999-cab9a90b13d8', N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/hotels/StrawberryEarlGreyLatte.jpg')
GO
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [account_number], [subname], [dob]) VALUES (N'123', N'sa', N'123', N'123', N'123', N'123', N'123', 123, N'123', N'123', N'123', N'123', NULL, NULL, NULL)
INSERT [dbo].[userDetail] ([user_id], [username], [user_password], [email], [phone], [name], [avatar], [role], [ggid], [whislist_id], [self_description], [salt], [account_number], [subname], [dob]) VALUES (N'9ad6286d-d08d-4397-8704-b8a1aff07309', N'duc', N'hck/iR1by2E1Tx3fjs/Jux05xR471uvfRDOiNV2tI/s=', N'duc@gmail.com', N'12312512556', N'Duc', N'D:/netbeanJavaWeb/testUpload/src/main/webapp/images/users/null', 1, NULL, NULL, N'asdasdasdasdasd', N'zL4M79NfLg0Yvi245WS9HRrdwvG7aT', N'7126381212836919283', N'Le Quy DD', CAST(N'2002-07-24' AS Date))
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__userDeta__F3DBC572501A3D9B]    Script Date: 10/21/2022 8:54:05 AM ******/
ALTER TABLE [dbo].[userDetail] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
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
ALTER TABLE [dbo].[HotelAmenities]  WITH CHECK ADD FOREIGN KEY([amenity_id])
REFERENCES [dbo].[Amenity] ([amenity_id])
GO
ALTER TABLE [dbo].[HotelAmenities]  WITH CHECK ADD FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Image]  WITH CHECK ADD  CONSTRAINT [FK__Image__hotel_id__6383C8BA] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Image] CHECK CONSTRAINT [FK__Image__hotel_id__6383C8BA]
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD  CONSTRAINT [FK__Rating__hotel_id__5070F446] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Rating] CHECK CONSTRAINT [FK__Rating__hotel_id__5070F446]
GO
ALTER TABLE [dbo].[Rating]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[userDetail] ([user_id])
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
ALTER TABLE [dbo].[Room]  WITH CHECK ADD  CONSTRAINT [FK__Room__hotel_id__534D60F1] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[Room] CHECK CONSTRAINT [FK__Room__hotel_id__534D60F1]
GO
ALTER TABLE [dbo].[RuleDetail]  WITH CHECK ADD  CONSTRAINT [FK__RuleDetai__hotel__5629CD9C] FOREIGN KEY([hotel_id])
REFERENCES [dbo].[Hotel] ([hotel_id])
GO
ALTER TABLE [dbo].[RuleDetail] CHECK CONSTRAINT [FK__RuleDetai__hotel__5629CD9C]
GO
USE [master]
GO
ALTER DATABASE [bookify] SET  READ_WRITE 
GO
