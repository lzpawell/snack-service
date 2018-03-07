import { SnackManagerPage } from './app.po';

describe('snack-manager App', function() {
  let page: SnackManagerPage;

  beforeEach(() => {
    page = new SnackManagerPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
