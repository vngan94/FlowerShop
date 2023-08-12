package com.tttn.flowershop.controller.dashboardController;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tttn.flowershop.model.Category;
import com.tttn.flowershop.model.Color;
import com.tttn.flowershop.model.Flower;
import com.tttn.flowershop.model.User;
import com.tttn.flowershop.service.FlowerService;
import com.tttn.flowershop.utils.Const;
import com.tttn.flowershop.utils.Global;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/admin/flower")
public class ManageFlowerController {
	private Flower flowerFirst;

	private static List<Flower> flowerList = new ArrayList<>();

	@RequestMapping(value="", method=RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("userLogin");
//		
//		if (user == null || user.getIdRole() == Const.User.idCustomerRole) {
//			return "pages/error404";
//		}

		flowerList = FlowerService.getListFlower();
		PagedListHolder pagedListHolder = new PagedListHolder(flowerList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(2);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);

//		model.addAttribute("role", Global.getRole(session));
		return "admin/flower";
	}
	
	@ModelAttribute("flowerNew")
	public Flower flowerNew() {
		return new Flower();
	}
	@ModelAttribute("flowerEdit")
	public Flower flowerEdit() {
		return new Flower();
	}

	@ModelAttribute("getListCL")
	public List<Color> getListColors() {
		return FlowerService.getListColors();
	}
	@ModelAttribute("getListCA")
	public List<Category> getListCategory() {
		return FlowerService.getListCategories();
	}

	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insert(ModelMap model, HttpServletRequest request, @ModelAttribute("flowerNew") Flower flowerNew, @RequestParam("photos") List<MultipartFile> photos, @RequestParam("quantity_new") Integer quantity_new  , BindingResult errors) {
		model.addAttribute("them_saidinhdang", errors.hasErrors());
		model.addAttribute("flowerNew", flowerNew);

		if(flowerNew.getName().trim().length() == 0) {
			errors.rejectValue("name", "flowerNew", "Vui lòng nhập họ tên");
		}
		if(flowerNew.getPrice() == null) {
			errors.rejectValue("price", "flowerNew", "Vui lòng chọn giá");
		}
		if(errors.hasErrors()) {
			model.addAttribute("message", "Vui lòng nhập đầy đủ thông tin!");
			return home(model, request);
		}
		flowerNew.setQuantity(quantity_new);
		List<String> listImages = new ArrayList<>();
		try {
			for ( MultipartFile photo : photos){
				String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_"));
				String fileName = date + photo.getOriginalFilename();
				String photoPath = Const.Flower.basePath + File.separator + fileName;
				photo.transferTo(new File(photoPath));
				Thread.sleep(1500);
				listImages.add(fileName);
			}
			flowerNew.setImages(listImages);
			model.addAttribute("insert", FlowerService.addFlower(flowerNew) != Const.Flower.idNoExistFlower);
			return home(model, request);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Thread thread = new Thread(() -> {
				try {
					for (String path: listImages){
						File oldFile = new File(Const.Flower.basePath + File.separator + path);
						System.out.println("Delete old Files: " + oldFile.delete());
					}
				} catch (Exception ex) {
					System.out.println("Error Delete Files");
				}
			});
			thread.setDaemon(true);
			thread.start();
			model.addAttribute("insert", false);
			model.addAttribute("flowerNew", flowerNew);
			return home(model, request);
		}
	}

	@RequestMapping(value="edit/{id}", method=RequestMethod.GET)
	public String showFormEdit(ModelMap model,HttpServletRequest request, @ModelAttribute("flowerEdit") Flower flowerEdit, @PathVariable String id) {
		
		model.addAttribute("formEdit", true);
		flowerFirst = FlowerService.getFlowerById(Integer.parseInt(id));
	
		model.addAttribute("flowerEdit", flowerFirst);
		return home(model, request);
	}

	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(ModelMap model,HttpServletRequest request, @ModelAttribute("flowerEdit") Flower flowerEdit, @RequestParam("photos") List<MultipartFile> photos, BindingResult errors ) {
		
		model.addAttribute("sua_saidinhdang", errors.hasErrors());
		model.addAttribute("flowerEdit", flowerEdit);
		if(flowerEdit.getName().trim().length() == 0) {
			errors.rejectValue("name", "flowerNew", "Vui lòng nhập họ tên");
		}
		if(flowerEdit.getPrice() == null) {
			errors.rejectValue("price", "flowerNew", "Vui lòng chọn giá");
		}
		if(errors.hasErrors()) {
			return home(model, request);
		}
		if (photos.size() == 0) {
			
			model.addAttribute("update", FlowerService.update(flowerEdit));
			model.addAttribute("flowerEdit", new Flower());
			return home(model, request);
		} else {
			List<String> listImages = new ArrayList<>();
			try {
				for ( MultipartFile photo : photos){
					String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_"));
					String fileName = date + photo.getOriginalFilename();
					String photoPath = Const.Flower.basePath + File.separator + fileName;
					photo.transferTo(new File(photoPath));
					Thread.sleep(1500);
					listImages.add(fileName);
				}
				flowerEdit.setImages(listImages);
				
				
				model.addAttribute("insert", FlowerService.updateFlower(flowerEdit) != Const.Flower.idExistFlower);

				return home(model, request);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Thread thread = new Thread(() -> {
					try {
						for (String path: listImages){
							File oldFile = new File(Const.Flower.basePath + File.separator + path);
							System.out.println("Delete old Files: " + oldFile.delete());
						}
					} catch (Exception ex) {
						System.out.println("Error Delete Files");
					}
				});
				thread.setDaemon(true);
				thread.start();
				model.addAttribute("update", false);
				
				model.addAttribute("flowerEdit", flowerEdit);
				return home(model, request);
			}
		}
	}

	@RequestMapping(value = "delete", method=RequestMethod.POST)
	public String delete(ModelMap model,HttpServletRequest request, @RequestParam("id") Integer id) {
		model.addAttribute("delete", FlowerService.delete(id) != Const.Flower.deleteFalse);
		return home(model, request);
	}

	@RequestMapping(value= "", params = "btnsearch")
	public String searchFlower(HttpServletRequest request, ModelMap model) {
		String text = request.getParameter("searchInput");
		
		
		List<Flower> flowers = flowerList.stream().filter(temp ->
			temp.getName().contains(text) || temp.getCategoriesName().contains(text) || temp.getColorsName().contains(text)
			).collect(Collectors.toList());


		PagedListHolder pagedListHolder = new PagedListHolder(flowers);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(2);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "admin/flower";
	}
}
