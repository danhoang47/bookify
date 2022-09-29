import headerStyles from "./Header.module.scss";
import IconButton from "../IconButton";
import SearchBox from "../SearchBox";
import ProfileHeaderNav from "../ProfileHeaderNav";
import { Logo } from '@/components'
import {
  faBookmark,
  faBell
} from '@fortawesome/free-solid-svg-icons'

const NotificationIconButton = IconButton;
const BookmarkIconButton = IconButton;

function Header() {

  return (
    <header id={headerStyles["header"]}>
      <div className={headerStyles["logo-wrapper"]}>
        <Logo >
          <h3>Bookify</h3>  
        </Logo>
      </div>
      <SearchBox />
      <div className={headerStyles["icon-buttons-container"]}>
        <BookmarkIconButton icon={faBookmark} />
        <NotificationIconButton icon={faBell} />
        <ProfileHeaderNav />
      </div>
    </header>
  );
}

export default Header;
