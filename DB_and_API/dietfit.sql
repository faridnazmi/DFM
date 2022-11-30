-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2021 at 09:21 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dietfit`
--

-- --------------------------------------------------------

--
-- Table structure for table `diary`
--

CREATE TABLE `diary` (
  `diary_id` int(11) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  `meals_name` varchar(100) NOT NULL,
  `meals_cal_info` varchar(100) NOT NULL,
  `ex_name` varchar(100) NOT NULL,
  `ex_cal_info` varchar(100) NOT NULL,
  `meals_note` text NOT NULL,
  `ex_note` text NOT NULL,
  `ex_time_taken` varchar(100) NOT NULL,
  `diary_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `diary`
--

INSERT INTO `diary` (`diary_id`, `user_email`, `meals_name`, `meals_cal_info`, `ex_name`, `ex_cal_info`, `meals_note`, `ex_note`, `ex_time_taken`, `diary_date`) VALUES
(187, 'testing@gmail.com', 'Belut Panggang', '', 'jogging', '', '', '', '', '2021-06-13 00:09:18'),
(189, 'farid@gmail.com', 'Belut Panggang', '', 'jogging', '', '', '', '', '2021-06-13 00:12:51'),
(198, 'testing@gmail.com', 'Belut Panggang', 'ushshs', 'jogging', 'hshshs', 'hdhshs', 'shshhs', '64644', '2021-06-13 13:59:28'),
(204, 'faridtest@gmail.com', 'Belut Panggang', '295', 'start jump', '200', 'eat 3 times in a day', '3 time done', '30', '2021-06-14 02:54:43'),
(207, 'faridnazmitest@gmail.com', 'Biskut Kosong', '40', 'Basketball', '584', 'Eat everyday with milo drink', 'play 2 games straight', '40', '2021-06-14 23:55:51');

-- --------------------------------------------------------

--
-- Table structure for table `health_tips`
--

CREATE TABLE `health_tips` (
  `tips_id` int(11) NOT NULL,
  `tips_name` varchar(50) NOT NULL,
  `tips_type` varchar(50) NOT NULL,
  `tips_desc` text NOT NULL,
  `tips_reference` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `health_tips`
--

INSERT INTO `health_tips` (`tips_id`, `tips_name`, `tips_type`, `tips_desc`, `tips_reference`) VALUES
(1, 'Drinking Water', 'health', 'The U.S. National Academies of Sciences, Engineering, and Medicine determined that an adequate daily fluid intake is:\r\nAbout 15.5 cups (3.7 liters) of fluids a day for men\r\nAbout 11.5 cups (2.7 liters) of fluids a day for women\r\n\r\nRead more on the website...\r\n', 'www.webmd.com/diet/features/6-reasons-to-drink-water#1'),
(2, 'Heart Disease and Stroke Prevention', 'health', '1.Eat healty diet.\r\n-Make smart choices like limiting refined carbohydrates, processed meats and sweetened drinks.\r\n2.Be physically active.\r\n-If you’re already active, you can increase your intensity for even more benefits.\r\n3.Watch your weight.\r\n- Lose weight if you’re overweight or obese.\r\n', 'www.heart.org/en/healthy-living/healthy-lifestyle/prevent-heart-disease-and-stroke'),
(3, 'Exercise Tips For Beginners', 'Fitness', '1.Set Realistic Goals.\r\n-It’s vital to break your ultimate goal down in to stages and set lots of smaller goals to keep you motivated along the way.\r\n2.Be Consistent.\r\n-Stick to your programme and avoid frequent stops and starts.\r\n3.Too much Too soon.\r\n-Increase what you’re doing by no more than 10% per week.', 'https://www.everyoneactive.com/content-hub/fitness/top-10-tips-beginners/'),
(4, 'Nutrition Tips for diet', 'Nutrition', '1.Include protein with every meal\r\n-Including some protein with every meal can help balance blood sugar.\r\n2.Get enough fiber\r\n-According to the AHA, fiber can help improve blood cholesterol levels and lower the risk of heart disease, obesity, and type 2 diabetes.\r\n3.Include healthful fats\r\n-A person can replace these fats with unsaturated fats, which they can find in foods such as vegitables oils.', 'https://www.medicalnewstoday.com/articles/nutrition-tips#what-to-eat'),
(5, 'Healthy Lifestyle Tips for Adult', 'Lifestyle', '1.Eat a variety of foods\r\n2.Base your diet on plenty of foods rich in carbohydrates\r\n3.Replace saturated with unsaturated fat\r\n4.Enjoy plenty of fruits and vegetables\r\n5.Reduce salt and sugar intake\r\n', 'https://www.eufic.org/en/healthy-living/article/10-healthy-lifestyle-tips-for-adults');

-- --------------------------------------------------------

--
-- Table structure for table `list_exercise`
--

CREATE TABLE `list_exercise` (
  `ex_name` varchar(100) NOT NULL,
  `ex_type` varchar(20) NOT NULL,
  `calories_info` varchar(100) NOT NULL,
  `ex_time_taken` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `list_exercise`
--

INSERT INTO `list_exercise` (`ex_name`, `ex_type`, `calories_info`, `ex_time_taken`) VALUES
('Basketball', 'Active sport', '584 calories', '30 minutes'),
('jogging', 'stamina', '200 calories', '40 mins'),
('Lunges', 'Static exercise', '9.3 calories', '1 min'),
('Mountain climbing', 'cardio exercise', '240 to 355.5 calories', '30 minutes'),
('start jump', 'cardio', '200 calories', '1 hour'),
('Walking moderately ', 'Relaxing exercise', '85 calories', '30 minutes');

-- --------------------------------------------------------

--
-- Table structure for table `list_meals`
--

CREATE TABLE `list_meals` (
  `meals_name` varchar(100) NOT NULL,
  `food_type` varchar(100) NOT NULL,
  `calories_info` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `list_meals`
--

INSERT INTO `list_meals` (`meals_name`, `food_type`, `calories_info`) VALUES
('Belut Panggang', 'More Iron moderate protein', '295 cal/96gram'),
('Bihun Goreng Cina', 'More Carbohydrate moderate protein', '296 cal/180gram'),
('Biskut Kosong', 'More Fibre Moderate Protein', '40 cal/8.5gram'),
('Bubur Ayam', 'More carbohydrate less fat', '70 kcal/200gram'),
('Maggi Goreng', 'More fat and unhealthy', '360 kcal/plate'),
('nasi goreng pattaya', 'more carbohydrate, moderate fat', '605 cal/plate'),
('nasi lemak', 'oily or fatty rice', '400 cal/plate'),
('roti canai', 'more carbohydrates, moderate fat', '350 cal/pcs');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_email` varchar(255) NOT NULL,
  `username_user` varchar(255) NOT NULL,
  `user_pwd` varchar(255) NOT NULL,
  `user_bmi` varchar(255) NOT NULL,
  `user_age` varchar(255) NOT NULL,
  `user_act_lvl` varchar(255) NOT NULL,
  `user_gender` varchar(255) NOT NULL,
  `malaysian_type` varchar(255) NOT NULL,
  `user_profile_pic` text NOT NULL,
  `calories_goal` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_email`, `username_user`, `user_pwd`, `user_bmi`, `user_age`, `user_act_lvl`, `user_gender`, `malaysian_type`, `user_profile_pic`, `calories_goal`) VALUES
('alif@gmail.com', 'alif', '$2y$10$hjrXZzbmMYfvmORRVyswq.9Iy0sB0NiB8/Ce7YGDHBvmxBoF.EuYG', '23', '23', '', 'Male', '', '', '2000'),
('farid@gmail.com', 'farid6945', '$2y$10$xMUcvn1avTxufbgo/zYlg.EKK8IkusmNx0u40rw70j1eVk0Doss7W', '27.92', '23', 'Extra Active', 'Male', '', 'http://192.168.100.196/Volley_Dietfit/profile_image/farid@gmail.com.jpeg', ''),
('faridnazmi@gmail.com', 'farid', '$2y$10$YlSIJeaR08CzcdsokQTm7eDFV5osZv1nJS30kg5EATQ4i8cysJdge', '27.55', '22', 'Light exercise', 'Male', 'Adult', '', '1290'),
('faridnazmitest@gmail.com', 'faridnazmi', '$2y$10$F9.Ilz4oKWDnzxBIy3t/ueqRzKnP6W93ySYIno3rjsxSbefq6CsEG', '27.5', '21', 'Little/no exercise', 'Male', 'Adult', '', '2129.24'),
('faridtest@gmail.com', 'farid6949', '$2y$10$dguIASN2w8kKHV.LqpDck.4Hz4edHh28yRoOT1LKsZyi9cllWYJdy', '27.5', '22', 'Little/no exercise', 'Male', 'Adult', '', '2129.24'),
('muhdfarid@gmail.com', 'faridnazmi99', '$2y$10$.WVDsHo0dc7K2JG1LjsmiuggMzI9ufO2en/Wm1sLahrxsRsDxtAby', '27.5', '22', 'Little/no exercise', 'Male', 'Adult', '', '2000'),
('testing@gmail.com', 'testing', '$2y$10$8tL3myKJSKZIC8LyeP1qc.fP3de/FzZ32r.rfuNvlu.zWb.mc16fO', '21.6', '21', '', 'Male', 'adult', '', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `diary`
--
ALTER TABLE `diary`
  ADD PRIMARY KEY (`diary_id`),
  ADD KEY `user_email` (`user_email`),
  ADD KEY `meals_name` (`meals_name`),
  ADD KEY `ex_name` (`ex_name`);

--
-- Indexes for table `health_tips`
--
ALTER TABLE `health_tips`
  ADD PRIMARY KEY (`tips_id`);

--
-- Indexes for table `list_exercise`
--
ALTER TABLE `list_exercise`
  ADD PRIMARY KEY (`ex_name`);

--
-- Indexes for table `list_meals`
--
ALTER TABLE `list_meals`
  ADD PRIMARY KEY (`meals_name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `diary`
--
ALTER TABLE `diary`
  MODIFY `diary_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=208;

--
-- AUTO_INCREMENT for table `health_tips`
--
ALTER TABLE `health_tips`
  MODIFY `tips_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `diary`
--
ALTER TABLE `diary`
  ADD CONSTRAINT `diary_ibfk_1` FOREIGN KEY (`user_email`) REFERENCES `user` (`user_email`),
  ADD CONSTRAINT `diary_ibfk_2` FOREIGN KEY (`meals_name`) REFERENCES `list_meals` (`meals_name`),
  ADD CONSTRAINT `diary_ibfk_3` FOREIGN KEY (`ex_name`) REFERENCES `list_exercise` (`ex_name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
