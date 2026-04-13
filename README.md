# 🎮 Truth or Dare - Android Game App

An interactive and feature-rich **Truth or Dare** mobile game built with **Kotlin** for Android. This app brings the classic party game to your smartphone with modern features, stunning animations, and customizable gameplay options.

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)
![Min SDK](https://img.shields.io/badge/Min%20SDK-21-orange.svg)
![License](https://img.shields.io/badge/License-MIT-red.svg)

---

## 📱 Screenshots

> Add your app screenshots here

---

## ✨ Features

### 🎭 Core Features
- **Player Management** - Add 2-8 players with custom emoji avatars
- **Bottle Spin Animation** - Smooth 3D bottle rotation with visual effects
- **Truth & Dare Questions** - 90+ unique questions across 3 difficulty levels
- **Scoring System** - Track player performance with points and statistics
- **Interactive Leaderboard** - View rankings, winners, and player stats

### 🌟 Advanced Features
- **Difficulty Levels** - Easy, Medium, and Hard modes with different question sets
- **Team Mode** - Divide players into Team A and Team B for competitive gameplay
- **Power-Up System** - Random special cards with unique abilities (30% chance)
  - Skip Card
  - Double Points
  - Swap Turn
  - Easy Mode
  - Wild Card
- **Custom Bottle Skins** - 6 different colored bottle designs
- **Splash Screen** - Professional app launch experience

### 🎨 Visual Effects & Animations
- **Glow Ring Effect** - Pulsing glow during bottle spin
- **Spinning Trail** - Golden trail following bottle rotation
- **Sparkle Particles** - 4 rotating sparkles around bottle
- **Rainbow Colors** - Bottle cycles through colors while spinning
- **Pulse Animations** - Selected player name grows and shrinks
- **Bounce Effects** - Power-up cards bounce into view
- **Slide Transitions** - Smooth button animations

---

## 🏗️ Architecture & Tech Stack

### Technologies Used
- **Language:** Kotlin
- **UI Framework:** XML Layouts (Traditional Android Views)
- **Min SDK:** Android 5.0 (API 21)
- **Target SDK:** Android 14 (API 34)

### Architecture Pattern
- **MVC-inspired** architecture
  - **Model:** Data classes (Player, PowerUp, BottleSkin)
  - **View:** XML layouts
  - **Controller:** Activity classes

### Key Android Components
- **Activities** - 5 screens for different app functionalities
- **RecyclerView** - Efficient list display for players and leaderboard
- **Parcelable** - Pass complex objects between activities
- **Intent** - Navigation and data transfer
- **Animations** - RotateAnimation, PropertyAnimator, ViewAnimator
- **CardView** - Material Design card components
- **Custom Drawables** - Vector graphics for bottle and effects

---

## 🎮 How to Use

### Adding Players
1. Enter player name
2. Click "Add" button
3. Select emoji avatar from dialog
4. Repeat for 2-8 players

### Game Settings
- **Difficulty:** Choose Easy, Medium, or Hard
- **Bottle Style:** Select from 6 color options
- **Team Mode:** Toggle on for team-based gameplay

### Playing the Game
1. Click "Start Game"
2. Press "Spin the Bottle"
3. Watch the bottle spin with effects
4. Selected player chooses Truth or Dare
5. Complete or skip the challenge
6. View updated scores and continue

### Viewing Results
- Click "View Leaderboard" anytime
- See rankings, scores, and stats
- View team winner (if team mode enabled)

---

## 🔧 Installation & Setup

### Prerequisites
- Android Studio (Arctic Fox or newer)
- Android SDK 21 or higher
- Kotlin plugin

### Steps to Run
1. **Clone the repository**
```bash
   git clone https://github.com/yourusername/truth-or-dare-app.git
```

2. **Open in Android Studio**
   - File → Open → Select project folder

3. **Sync Gradle**
   - Click "Sync Now" when prompted

4. **Run the app**
   - Click the green Run button ▶️
   - Select emulator or connected device

---

## 📊 Game Statistics

- **Total Questions:** 90+ (45 truths, 45 dares)
- **Difficulty Levels:** 3 (Easy, Medium, Hard)
- **Player Capacity:** 2-8 players
- **Power-Up Types:** 5 unique cards
- **Bottle Skins:** 6 color variants
- **Visual Effects:** 8+ animations

---

## 🎯 Scoring System

| Action | Base Points | Difficulty Bonus |
|--------|-------------|------------------|
| **Truth Completed** | 10 | +0/5/10 |
| **Dare Completed** | 20 | +0/5/10 |
| **Challenge Skipped** | 0 | - |
| **Power-Up (Double Points)** | 2x multiplier | - |

**Difficulty Bonuses:**
- Easy: +0 points
- Medium: +5 points
- Hard: +10 points

---

## 🌈 Unique Features

What makes this app stand out:

1. **Emoji Personalization** - Players choose avatars for unique identity
2. **Multi-Level Difficulty** - Suitable for all age groups
3. **Power-Up Mechanics** - Adds unpredictability and excitement
4. **Team Competition** - Social gameplay with team rivalry
5. **Professional Animations** - Smooth, eye-catching visual effects
6. **Complete Statistics** - Track every player's performance
7. **Customizable Experience** - Multiple bottle styles and game modes

---

## 🛠️ Built With

- **[Kotlin](https://kotlinlang.org/)** - Programming language
- **[Android SDK](https://developer.android.com/)** - Mobile framework
- **[Material Design](https://material.io/)** - UI components
- **[RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)** - List display
- **[CardView](https://developer.android.com/guide/topics/ui/layout/cardview)** - Card layouts
- **[Vector Drawables](https://developer.android.com/guide/topics/graphics/vector-drawable-resources)** - Scalable graphics

---

## 📱 System Requirements

### Minimum Requirements
- **Android Version:** 5.0 (Lollipop)
- **API Level:** 21
- **RAM:** 2GB
- **Storage:** 50MB

### Recommended
- **Android Version:** 8.0 (Oreo) or higher
- **API Level:** 26+
- **RAM:** 4GB
- **Storage:** 100MB

---

## 🐛 Known Issues

- None currently reported

---

## 🔮 Future Enhancements

- [ ] Online multiplayer mode
- [ ] Custom question creation
- [ ] Dark mode support
- [ ] Sound effects and background music
- [ ] Save game progress
- [ ] Share results on social media
- [ ] More power-up types
- [ ] Achievement system
- [ ] Multiple language support
- [ ] Timer for dare challenges

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your Profile](https://linkedin.com/in/yourprofile)
- Email: your.email@example.com

---

## 🙏 Acknowledgments

- Inspired by the classic Truth or Dare party game
- Vector graphics created using Android Vector Drawable tools
- Icons and design inspiration from Material Design guidelines
- Special thanks to the Android development community

---

## 📸 Demo Video

> Add a link to your demo video here (YouTube/Google Drive)

---

## ⭐ Show Your Support

Give a ⭐️ if you like this project!

---

## 📞 Contact & Support

For questions or support:
- Open an issue on GitHub
- Email: your.email@example.com

---

**Made with ❤️ using Kotlin and Android Studio**
